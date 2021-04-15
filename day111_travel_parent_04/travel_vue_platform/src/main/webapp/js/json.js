/**
 *  通过表单对象调用此方法
 *  将表单所有的键和值转成一个JSON字符串
 */
$.fn.extend({
    "toJsonString": function() {
        //创建一个临时对象
        var formObject = {};
        //调用方法，将表单中所有的项转成一个数组，数组中每个元素是一个JSON对象，每个JSON对象包含name，value属性
        var formArray =$(this).serializeArray();
        //遍历数组，将每个元素的name和value取出，并且做为临时对象的属性和值
        $.each(formArray,function(i,e){
            formObject[e.name] = e.value;
        });
        //将得到的JSON对象再转成字符串返回
        return JSON.stringify(formObject);
    }
});
