package best.prog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedEntityGraphs({
  @NamedEntityGraph(
      name = "Group.findWithUser", 
      attributeNodes = {
          @NamedAttributeNode(value = "groupUsers", subgraph = "subUser")
      },
      subgraphs = {
          @NamedSubgraph(name = "subUser", attributeNodes = {
              @NamedAttributeNode("user")
          })
      }
  )
})
@SuppressWarnings("serial")
@Entity
@Table(name = "GRP")
public class Group extends BaseEntity {
  
  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
  private List<RoleGroup> roleGroups = new ArrayList<RoleGroup>();
  
  @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
  private List<GroupUser> groupUsers = new ArrayList<GroupUser>();
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RoleGroup> getRoleGroups() {
    return roleGroups;
  }

  public void setRoleGroups(List<RoleGroup> roleGroups) {
    this.roleGroups = roleGroups;
  }

  public List<GroupUser> getGroupUsers() {
    return groupUsers;
  }

  public void setGroupUsers(List<GroupUser> groupUsers) {
    this.groupUsers = groupUsers;
  }

}
