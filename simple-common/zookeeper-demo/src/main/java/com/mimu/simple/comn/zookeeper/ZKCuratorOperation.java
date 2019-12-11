package com.mimu.simple.comn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;

/**
 author: mimu
 date: 2019/12/2
 */
@Slf4j
public class ZKCuratorOperation {
    private static volatile CuratorFramework curatorClient;
    private static final Object dcl = new Object();

    static {
        if (curatorClient == null) {
            synchronized (dcl) {
                if (curatorClient == null) {
                    curatorClient = CuratorFrameworkFactory.builder().
                            connectString("localhost:2181").
                            connectionTimeoutMs(2000).
                            sessionTimeoutMs(5000).
                            retryPolicy(new ExponentialBackoffRetry(1000, 1)).
                            namespace("customer").
                            build();
                    curatorClient.start();
                }
            }
        }
    }

    public static boolean createPersistentNode(String path) throws Exception {
        try {
            curatorClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path);
            return true;
        } catch (Exception e) {
            log.error("createPersistentNode error", e);
            return false;
        }
    }

    public static boolean createPersistentNode(String path, String data) throws Exception {
        try {
            curatorClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, data.getBytes());
            return true;
        } catch (Exception e) {
            log.error("createPersistentNode error", e);
            return false;
        }
    }

    public static boolean createEphemeralNode(String path) throws Exception {
        try {
            curatorClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path);
            return true;
        } catch (Exception e) {
            log.error("createEphemeralNode error", e);
            return false;
        }
    }

    public static boolean createEphemeralNode(String path, String data) throws Exception {
        try {
            curatorClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, data.getBytes());
            return true;
        } catch (Exception e) {
            log.error("createEphemeralNode error", e);
            return false;
        }
    }

    public static String readNode(String path) throws Exception {
        try {
            byte[] bytes = curatorClient.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            log.error("readNode error", e);
            return null;
        }
    }

    public static boolean setData(String path) throws Exception {
        try {
            curatorClient.setData().forPath(path);
            return true;
        } catch (Exception e) {
            log.error("setData error", e);
            return false;
        }
    }

    public static List<String> getChildren(String path) throws Exception {
        try {
            return curatorClient.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("getChildren error", e);
            return Collections.emptyList();
        }
    }

    public static boolean deleteNode(String path) throws Exception {
        try {
            curatorClient.delete().forPath(path);
            return true;
        } catch (Exception e) {
            log.error("deleteNode error", e);
            return false;
        }
    }

    public static boolean checkExist(String path) throws Exception {
        try {
            Stat stat = curatorClient.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            log.error("checkExist error", e);
            return false;
        }
    }

    /**
     * NodeCache: 对一个节点进行监听，监听事件包括指定的路径节点的增、删、改的操作 包括数据变更
     *
     *
     * 节点路径不存在，set不触发监听
     * 节点路径不存在，创建事件触发监听（第一次创建时要触发）
     * 节点路径存在，set触发监听（改操作触发）
     * 节点路径存在，delete触发监听（删操作触发）
     *
     * 节点挂掉，未触发任何监听
     * 节点重连，未触发任何监听
     * 节点重连 ，恢复监听
     * @param curatorClient
     * @param path
     * @param cache
     * @return
     */
    public static boolean addNodeListener(CuratorFramework curatorClient, String path, boolean cache) {
        NodeCache nodeCache = new NodeCache(curatorClient, path, cache);
        try {
            /**
             * 如果为true则首次不会缓存节点内容到cache中，默认为false,设置为true首次不会触发监听事件
             */
            nodeCache.start(false);
            nodeCache.getListenable().addListener(() -> {
                byte[] data = nodeCache.getCurrentData().getData();
                log.info("nodeData changed:{}", new String(data));
            });
            return true;
        } catch (Exception e) {
            log.error("addNodeListener error", e);
            return false;
        }

    }

    /**
     * PathChildrenCache: 对指定的路径节点的一级子目录进行监听，不对该节点的操作进行监听，对其子目录的节点进行增、删、改的操作监听
     *
     *
     * 注册子节点触发type=[CHILD_ADDED]
     * 更新触发type=[CHILD_UPDATED]
     * zk挂掉type=CONNECTION_SUSPENDED,，一段时间后type=CONNECTION_LOST
     * 重启zk：type=CONNECTION_RECONNECTED,
     * 更新子节点：type=CHILD_UPDATED,
     * 删除子节点type=CHILD_REMOVED
     *
     * 更新父节点：不触发
     * 删除父节点：不触发  无异常
     * 创建父节点：不触发
     * 再创建及更新子节点不触发
     *
     * @param curatorClient
     * @param path
     * @param cache
     * @return
     */
    public static boolean addPathChildListener(CuratorFramework curatorClient, String path, boolean cache) {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorClient, path, false);
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            pathChildrenCache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        log.info("child listener add child node:{}", event.getData());
                        break;
                    case CHILD_UPDATED:
                        log.info("child listener child node update:{}", event.getData());
                        break;
                    case CHILD_REMOVED:
                        log.info("child listener child node delete:{}", event.getData());
                        break;
                    case CONNECTION_SUSPENDED:
                    case CONNECTION_LOST:
                    case CONNECTION_RECONNECTED:
                    case INITIALIZED:
                        break;
                }
            });
            return true;
        } catch (Exception e) {
            log.error("addPathChildListener error", e);
            return false;
        }
    }

    /**
     * TreeCache:  可以将指定的路径节点作为根节点（祖先节点），对其所有的子节点操作进行监听，呈现树形目录的监听，
     * 可以设置监听深度，最大监听深度为2147483647（int类型的最大值）
     *
     * TreeCache.nodeState == LIVE的时候，才能执行getCurrentChildren非空,默认为PENDING
     * 初始化完成之后，监听节点操作时 TreeCache.nodeState == LIVE
     * maxDepth值设置说明，比如当前监听节点/t1，目录最深为/t1/t2/t3/t4,则maxDepth=3,说明下面3级子目录全
     * 监听，即监听到t4，如果为2，则监听到t3,对t3的子节点操作不再触发
     * maxDepth最大值2147483647
     * 初次开启监听器会把当前节点及所有子目录节点，触发[type=NODE_ADDED]事件添加所有节点（小等于maxDepth目录）
     * 默认监听深度至最低层
     * 初始化以[type=INITIALIZED]结束
     * [type=NODE_UPDATED],set更新节点值操作，范围[当前节点，maxDepth目录节点](闭区间)
     * [type=NODE_ADDED] 增加节点 范围[当前节点，maxDepth目录节点](左闭右闭区间)
     * [type=NODE_REMOVED] 删除节点， 范围[当前节点， maxDepth目录节点](闭区间),删除当前节点无异常
     * 事件信息
     * TreeCacheEvent{type=NODE_ADDED
     *
     * @param curatorClient
     * @param path
     * @param cache
     * @return
     */
    public static boolean addTreeListener(CuratorFramework curatorClient, String path, boolean cache) {
        TreeCache treeCache = new TreeCache(curatorClient, path);
        try {
            treeCache.start();
            treeCache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    case NODE_ADDED:
                        log.info("tree listener node add {}", event.getData().toString());
                        break;
                    case NODE_UPDATED:
                        log.info("tree listener node update {}", event.getData().toString());
                        break;
                    case NODE_REMOVED:
                        log.info("tree listener node delete {}", event.getData().toString());
                        break;
                    case CONNECTION_SUSPENDED:
                        break;
                    case CONNECTION_RECONNECTED:
                        break;
                    case CONNECTION_LOST:
                        break;
                    case INITIALIZED:
                        log.info("tree listener init operation");
                        break;
                    default:
                        break;
                }
            });
            return true;
        } catch (Exception e) {
            log.error("addTreeListener error", e);
            return false;
        }
    }

}
