<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        jQuery("#album_table").jqGrid({
            styleUI:"Bootstrap",
            url : "${pageContext.request.contextPath}/album/show",
            datatype : "json",
            height : 400,
            autowidth:true,
            colNames : [ 'ID','作者' ,'专辑名', '封面', '章节数量', '评分', '简介',"创建时间" ],
            colModel : [
                {name : 'id',align:"center"},
                {name : 'author',editable:true,align:"center",edittype:"select",editoptions:{}},
                {name : 'title',editable:true,align:"center"},
                {name : 'cover',editable:true,align:"center",edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/back/album/images/"+cellvalue+"'>";
                    }
                },
                {name : 'count',editable:true,align:"center"},
                {name : 'score',align:"center",editable:true},
                {name : 'brife',editable:true,align:"center"},
                {name : 'createDate',align:"center"}
            ],
            rowNum : 3,
            rowList : [ 3, 5, 10],
            pager : '#album_page',
            viewrecords : true,
            subGrid : true,
            caption : "专辑列表",
            editurl:"${pageContext.request.contextPath}/album/edit" ,

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
                        url: "${pageContext.request.contextPath}/chapter/show?albumId=" + id,
                        datatype: "json",
                        colNames: ['ID ', '歌手', '歌名', '时长', '大小','创建时间','在线播放'],
                        colModel: [
                            {name: "id",align:"center"},
                            {name: "singer",editable:true,align:"center"},
                            {name: "name" ,edittype:"file",editable:true},
                            {name: "duration",align:"center"},
                            {name: "size",align:"center"},
                            {name: "createDate",align:"center"},
                            {name: "opersion",width:300,formatter:function(cellvalue, options, rows){
                                    return "<audio controls>" +
                                        "<source src='${pageContext.request.contextPath}/back/album/audio/"+rows.name+"' " +
                                        "</audio>";}}
                        ],
                        rowNum: 2,
                        pager: pager_id,
                        height:200,
                        viewrecords : true,
                        editurl : "${pageContext.request.contextPath}/chapter/edit?albumId=" + id//定义对form编辑url
                    }).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : false,
                        add : true,
                        del : false,
                        search:false}, {
                        //控制修改
                        closeAfterEdit:true,                            //修改后关闭弹窗
                        beforeShowForm:function (fmt) {
                            fmt.find("#cover").attr("disabled",true);
                        }
                    }, {//控制添加
                        closeAfterAdd:true,
                        afterSubmit:function (data) {
                            console.log(data);
                            var cid = data.responseJSON.id;
                            var status = data.responseJSON.status;
                            if(status){
                                $.ajaxFileUpload({
                                    url:"${pageContext.request.contextPath}/chapter/upload",
                                    type:"post",
                                    data:{id:cid,albumId:id},
                                    fileElementId: "name",
                                    success:function (response) {
                                        //自动刷新jqgrid表格
                                        $("#album_table").trigger("reloadGrid");
                                    }
                                })
                            }
                        },

                    }
                );

            }
        }).navGrid("#album_page",{edit:true,add:true,del:true},{
            //修改
            closeAfterEdit:true,                            //修改后关闭弹窗
            beforeShowForm:function (fmt) {
                fmt.find("#cover").attr("disabled",true);
                $.ajax({
                    url:"${pageContext.request.contextPath}/star/showAll",
                    dataType:"JSON",
                    type:"post",
                    success:function(result){
                        $("select").attr("name","starId");
                        // $("select").attr(name,"author");
                        $.each(result,function(index,item){
                            var option = "<option role='option' value='"+item['nickname']+"'>"+item['nickname']+"</option>";
                            $("select").append(option);
                        });

                    },

                })
            }
        },{
            //添加
            beforeShowForm:function (fmt) {

                //console.log(typeof ());

               $.ajax({
                   url:"${pageContext.request.contextPath}/star/showAll",
                   dataType:"JSON",
                   type:"post",
                   success:function(result){
                       $("select").attr("name","starId");
                      // $("select").attr(name,"author");
                       $.each(result,function(index,item){
                           var option = "<option role='option' value='"+item['id']+"'>"+item['nickname']+"</option>";
                           $("select").append(option);
                       });

                   },

               })
            }
            ,

            afterSubmit:function (data) {
                console.log(data);
                var id = data.responseJSON.id;
                var status = data.responseJSON.status;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/upload",
                        type:"post",
                        data:{id:id},
                        fileElementId: "cover",
                        success:function (response) {
                            //自动刷新jqgrid表格
                            $("#album_table").trigger("reloadGrid");
                        }
                    })
                }
            },
            closeAfterAdd:true
        })
    })
</script>



<table id="album_table"></table>
<div id="album_page"></div>