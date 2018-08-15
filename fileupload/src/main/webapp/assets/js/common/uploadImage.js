/**
 * 图片上传公共方法   --需初始化绑定浏览按钮
 * @param showImg        上传成功后  执行的js方法  用户自定义（必填）
 * @param type           上传图片的类型====在基础数据系统-数据字典中设置-上传图片类型中添加
 * @param button_id      上图图片的按钮（必填）
 * @param file_size      图片大小,单位kb，默认50（选填）
 * @param path           追加自定义path（选填)
 *        最终URL：type/时间/path/name.jsp
 */
function initUploadImage(showImg, type, button_id, file_size, path) {
    var swfuOption = {//swfupload选项
        upload_url: "/fileUpload/upload2.action", //接收上传的服务端url
        flash_url: "/asset/js/control/SWFUpload/Flash/swfupload.swf",//swfupload压缩包解压后swfupload.swf的url
        file_post_name: 'image', //相当于用普通的文件域上传文件时的name属性，服务器端接收页面通过该名称来获取上传的文件
        button_placeholder_id: button_id,//上传按钮占位符的id
        file_size_limit: "20480",//用户可以选择的文件大小，有效的单位有B、KB、MB、GB，若无单位默认为KB
        button_width: 200, //按钮宽度
        button_height: 20, //按钮高度
        // button_text: '<span>选择文件</span>',//按钮文字
        button_image_url: "/asset/css/images/liulan_btn.png", //加上背景图片
        upload_success_handler: function (file, serverData, responseReceived) { //uploadSuccess函数的监听函数
            //上传成功回调
            var serverDataJson = eval('(' + serverData + ')');
            console.log(serverDataJson);
            if (!serverDataJson) {
                alert("图片上传报错，请重新上传！");
                return;
            }
            if (serverDataJson.status == -1) {
                alert("图片上传报错，请重新上传！");
                return;
            }
            serverDataJson.buttonId = button_id;
            serverData = JSON.stringify(serverDataJson);
            showImg(serverData);
        },
        file_dialog_complete_handler: function (selectNum, queueNum) {
            if (selectNum > 0 && queueNum > 0) {
                swfu.startUpload(); //开始上传队列中指定的文件
            }
        }
    };
    var swfu = new SWFUpload(swfuOption);//初始化并将swfupload按钮替换swfupload占位符
}