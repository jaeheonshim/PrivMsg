package com.jaeheonshim.privmsg.util;

import com.jaeheonshim.privmsg.DatabaseManager;
import com.jaeheonshim.privmsg.schema.Group;
import com.jaeheonshim.privmsg.schema.GroupMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupManager {
    private Set<Group> groups = new HashSet<>();

    public void loadGroups() {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM groups");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                groups.add(new Group(rs.getInt("rowid"), getGroupMember(rs.getString("leaderUid")), getMembersOfGroup(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group getFromDatabase(int id) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM groups WHERE rowid = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Group(id, getGroupMember(rs.getString("leaderUid")), getMembersOfGroup(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GroupMember getGroupMember(String uid) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM members WHERE uid = ?");
            stmt.setString(1, uid);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new GroupMember(uid, rs.getInt("groupId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<GroupMember> getMembersOfGroup(int groupId) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM members WHERE rowid = ?");
            stmt.setInt(1, groupId);

            ResultSet rs = stmt.executeQuery();

            List<GroupMember> members = new ArrayList<>();
            while (rs.next()) {
                members.add(new GroupMember(rs.getString("uid"), groupId));
            }

            return members;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
