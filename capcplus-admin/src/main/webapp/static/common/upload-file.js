/**
 *  文件上传
 *
 * @param maxSize 最大尺寸
 * @param _this 当前对象
 * @author zhangjie
 * @date 2022/6/2
 * @return
 */
function ajaxFileUpload(_this, maxSize){
    var obj = _this;
    var parentsObj = obj.parent();
    var extStart = obj.val().lastIndexOf(".");
    var suffix = obj.val().substring(extStart,obj.val().length);
    if(suffix != '.jpg' && suffix != '.png' && suffix != '.gif' && suffix != '.JPG'  && suffix != '.PNG'  && suffix != '.GIF' && suffix != '.jpeg'){
        notify.error('上传失败，只能上传后缀为：jpg、png、gif、JPG、jpeg 的图片哦');
        return;
    }
    //判断文件大小
    maxSize = !maxSize ? 500 : maxSize;	//文件最大为500kb
    var size = obj[0].files[0].size;
    var fileSize = (size / 1024).toFixed(2);
    if(fileSize > maxSize){
        notify.error("上传失败，文件大小不能超过"+ maxSize +"kb！");
        return;
    }
    var id = obj.attr("id");
    var url = "/file-img-upload";
    $.ajaxFileUpload({
        url: url, // 需要链接到服务器地址
        secureuri: false,
        fileElementId: id,  // 文件选择框的id属性
        dataType: 'json', // 服务器返回的格式，可以是json
        type : 'post',
        success:function (data, status){
            if(data.code="0000"){
                parentsObj.find(".hidePicValue").val(data.saveName);
                parentsObj.find(".fileupload").remove();
                parentsObj.find('label').css({"background-image": "url(/resources"  + "/images/" + data.saveName,"background-size":'cover'});
                parentsObj.find('label').find('i').remove();
                notify.success("图片上传成功");
            }else{
                notify.error(data.info);
            }
        },
        error: function (data, status, e){
            notify.error("上传失败！");
            console.log(e);
        }
    });
}

