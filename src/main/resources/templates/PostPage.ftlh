<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="commentForm" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <div class="comments-area">
            <#if user??>
                <form class="form" method="post" action="">
                    <textarea id="text" name="text">${commentForm.text!}</textarea>
                    <@c.error "commentForm.text"/>
                    <input type="submit" value="Send comment">
                </form>
            </#if>
            <div class="datatable comments-area">
                <table>
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Text</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list post.comments as com>
                        <tr class="comment">
                            <td class="author">
                                <div class="avatar">
                                    <img src="<@spring.url '/img/default-avatar.jpg'/>"/>
                                </div>
                                <a href="/user/${com.user.id}">${com.user.login}</a>
                            </td>
                            <td class="text">${com.text}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <#else>
        No such post
    </#if>
</@c.page>
