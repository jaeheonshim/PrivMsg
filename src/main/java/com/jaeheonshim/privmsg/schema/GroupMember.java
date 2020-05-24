package com.jaeheonshim.privmsg.schema;

public class GroupMember {
    private String uid;
    private int groupId;

    public GroupMember(String uid, int groupId) {
        this.uid = uid;
        this.groupId = groupId;
    }
}
