<%@page pageEncoding="UTF-8" isELIgnored="false" %>
    <script>
        $(function(){
            <!--创建表格-->
            $("#carouselTable").jqGrid({ //书写的全部是初始化参数
                styleUI:"Bootstrap",
                url:"${pageContext.request.contextPath}/banner/show",//用来获取数据远程地址 项目名/user/findAll
                datatype:"json",//用来指定本次请求的数据为json格式
                colNames:["ID","名称","图片","描述","状态","创建时间"],//表格标题
                cellEdit:true,//开启单元格编辑功能
                colModel:[  //用来指定标题对应获取json中哪个key的数组作为本列的值
                    {name:"id",align:"center"},//colmodel参数全部写在  列属性都写在对应的列里面
                    {name:"bannerName",align:"center",editable:true},
                    {name:"cover",align:"center",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                            return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/back/image/"+cellvalue+"'>";
                        }
                    },
                    {name:"description",align:"center",editable:true},
                    {name:"status",align:"center",editable:true,edittype:"select",editoptions:{value:"正常:正常;冻结:冻结"}},
                    {name:"createDate",align:"center"}
                ],
                autowidth:true,//表格铺满全屏
                height:200,
                rowNum : 3,//每页初始显示条数
                rowList : [ 3,5,10 ],//设置每页显示条数
                pager : '#banner-page',//设置分页栏所在div
                viewrecords:true,//是否显示总条数
                caption : "轮播图列表",//表格名称
                editurl : "${pageContext.request.contextPath}/banner/edit",//定义对form编辑url
                //设置表格的增删改查
            }).navGrid("#banner-page", {edit : true,add : true,del : true},{
                //控制修改
                closeAfterEdit:true,                            //修改后关闭弹窗
                beforeShowForm:function (fmt) {
                    fmt.find("#cover").attr("disabled",true);
                }

            },{
                //控制添加
                closeAfterAdd:true,
                afterSubmit:function(data){
                    console.log(data);
                    var status =data.responseJSON.status;
                    var id = data.responseJSON.id;
                    if(status){
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/banner/upload",
                            type:"post",
                            data:{id:id},
                            fileElementId: "cover",
                            success:function (response) {
                                //自动刷新jqgrid表格
                                $("#carouselTable").trigger("reloadGrid");
                            }
                        })
                    }
                }
            });
        });
    </script>


    <table id="carouselTable"></table>
    <div id="banner-page" ></div>
