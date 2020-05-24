package com.jaeheonshim.privmsg.schema;

import java.util.List;

public class Group {
    private int id;
    private GroupMember leader;
    private List<GroupMember> members;

    public Group(int id, GroupMember leader, List<GroupMember> members) {
        this.id = id;
        this.leader = leader;
        this.members = members;
    }
}
