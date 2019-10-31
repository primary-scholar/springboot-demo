package com.mimu.simple.springboot.demo.request;

/**
 * author: mimu
 * date: 2019/10/9
 */

public class UserInfoRequest {
    private long pid;
    private String name;
    private UserType type;
    private TermInfo termInfo;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(int type) {
        this.type = UserType.userType(type);
    }

    public TermInfo getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(int termId,int personId) {
        TermInfo termInfo = new TermInfo();
        termInfo.setTermId(termId);
        termInfo.setPersonId(personId);
        this.termInfo = termInfo;
    }

    class TermInfo{
        private int termId;
        private int personId;

        public int getTermId() {
            return termId;
        }

        public void setTermId(int termId) {
            this.termId = termId;
        }

        public int getPersonId() {
            return personId;
        }

        public void setPersonId(int personId) {
            this.personId = personId;
        }

        @Override
        public String toString() {
            return "TermInfo{" +
                    "termId=" + termId +
                    ", personId=" + personId +
                    '}';
        }
    }

    enum UserType {
        Error(-1), Publish(1), Media(1);

        private int type;

        UserType(int i) {
            this.type = i;
        }

        public int getType() {
            return type;
        }

        public static UserType userType(int type) {
            for (UserType userType : UserType.values()) {
                if (userType.getType() == type) {
                    return userType;
                }
            }
            return Error;
        }
    }
}
