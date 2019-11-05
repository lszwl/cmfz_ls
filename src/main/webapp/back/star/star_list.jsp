<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        jQuery("#starTable").jqGrid(
            {
                styleUI:"Bootstrap",
                url : "${pageContext.request.contextPath}/star/show",
                datatype : "json",
                height : 200,
                autowidth:true,
                colNames : [ 'ID', '艺名', '真名', '照片', '状态','性别', '创建时间' ],
                colModel : [
                    {name : 'id',align:"center"},
                    {name : 'nickname',editable:true,align:"center"},
                    {name : 'realname',editable:true,align:"center"},
                    {name : 'photo',editable:true,align:"center",edittype:"file",formatter:function(cellvalue, options, rowObject){
                          return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/back/star/images/"+cellvalue+"'>";
                      }
                    },
                    {name : 'status',align:"center",editable:true},
                    {name : 'sex',editable:true,align:"center"},
                    {name : 'createDate',align:"center"}
                ],
                rowNum : 3,
                rowList : [ 3, 5, 10],
                pager : '#star_page',
                viewrecords : true,
                subGrid : true,
                caption : "明星列表",
                editurl:"${pageContext.request.contextPath}/star/edit" ,

                subGridRowExpanded : function(subgrid_id, id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            styleUI:"Bootstrap",
                            autowidth:true,
                            url: "${pageContext.request.contextPath}/user/show?starId=" + id,
                            datatype: "json",
                            colNames: ['ID', '昵称', '城市', '签名', '头像','性别','创建时间'],
                            colModel: [
                                {name: "id",align:"center"},
                                {name: "nickname",editable:true,align:"center"},
                                {name: "province",editable:true,align:"center"},
                                {name: "sign",editable:true,align:"center"},
                                {name: "photo",editable:true,edittype:"file",align:"center"},
                                {name: "sex",editable:true,align:"center"},
                                {name: "createDate",align:"center"}
                            ],
                            rowNum: 2,
                            pager: pager_id,
                            height: '100%'
                            //editurl : "${pageContext.request.contextPath}/user/edit",//定义对form编辑url
                            }).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : false,
                            del : false,
                        search:false}, {
                            //控制修改
                                closeAfterEdit:true,                            //修改后关闭弹窗
                                beforeShowForm:function (fmt) {
                                    fmt.find("#cover").attr("disabled",true);
                                }
                        }, {//控制添加


                        }
                        );

                }
             }).navGrid("#star_page",{edit:true,add:true,del:true},{
                //控制修改
                closeAfterEdit:true,                            //修改后关闭弹窗
                beforeShowForm:function (fmt) {
                fmt.find("#photo").attr("disabled",true);
            }
             },{
            //控制添加
            closeAfterAdd:true,
            afterSubmit:function (data) {
                console.log(data);
                var id = data.responseJSON.id;
                var status = data.responseJSON.status;
                console.log(status);
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/star/upload",
                        type:"post",
                        data:{id:id},
                        fileElementId: "photo",
                        success:function (response) {
                            //自动刷新jqgrid表格
                            $("#starTable").trigger("reloadGrid");
                        }
                    })
                }
            }

        })

    })
</script>

<table id="starTable"></table>
<div id="star_page"></div>