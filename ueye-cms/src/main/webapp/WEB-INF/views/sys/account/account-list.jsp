<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

list<br/>

<shiro:hasRole name="admin">admin</shiro:hasRole><br/>
<shiro:hasRole name="user">user</shiro:hasRole><br/>
<shiro:hasRole name="test">test</shiro:hasRole><br/>

<shiro:hasPermission name="p_admin">p_admin</shiro:hasPermission><br/>


<shiro:hasPermission name="p_admin_">p_admin</shiro:hasPermission><br/>


<shiro:hasPermission name="account:editNew">new</shiro:hasPermission><br/>