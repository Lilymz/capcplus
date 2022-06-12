$(function () {
    //methods
    var fullPage = $('#fullpage').fullpage({
        verticalCentered: true,
        anchors: ['index', 'scene', 'server','api'],
        menu: '.main_header',
        fixedElements:'.main_header',
        css3: true,
    });
    /*header*/
    var swiper = new Swiper('.swiper-container.banner1', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        speed:500,
        centeredSlides: true,
        autoplay: 4000,
        grabCursor : true,
        loop:true,
        effect : 'coverflow',
        autoplayDisableOnInteraction: false,
        onInit:function (swiper) {
            swiperAnimateCache(swiper);
            swiperAnimate(swiper);
        },
        onSlideChangeEnd:function (swiper) {
            swiperAnimate(swiper);
        }
    });
    /*server swipper*/
    var bannerSwiper2 = new Swiper('.banner2-container',{
        pagination: {
            el:'.banner2-pagination',
            type:'fraction',
        },
        paginationClickable: true,
        speed:600,
        centeredSlides: true,
        autoplay: 2000,
        grabCursor : true,
        loop:true,
        effect : 'slide',
        autoplayDisableOnInteraction: false,
    })
    /*菜单控制*/
    $(window.document).on("mouseover", "#robot", function () {
        $(this).attr("src","/resources/assets/home/others/kefu_im.png");
    });
    $(window.document).on("mouseout", "#robot", function () {
        $(this).attr("src","/resources/assets/home/others/kefu_im_white.png");
    });
    $(window.document).on("mouseover", "#dimension", function () {
        $(this).attr("src","/resources/assets/home/others/dimension_gray.png");
    });
    $(window.document).on("mouseout", "#dimension", function () {
        $(this).attr("src","/resources/assets/home/others/dimension.png");
    });
    $(window.document).on("mouseover", "#top", function () {
        $(this).attr("src","/resources/assets/home/others/top_gray.png");
    });
    $(window.document).on("mouseout", "#top", function () {
        $(this).attr("src","/resources/assets/home/others/top.png");
    });
    /*api部分按钮控制*/
    $(window.document).on("click", "#btn-all", function () {
        $(this).addClass('btn-active')
        $('#btn-cverify').removeClass('btn-active');
    });
    $(window.document).on("click", "#btn-cverify", function () {
        $(this).addClass('btn-active')
        $('#btn-all').removeClass('btn-active');
    });
})
/*关闭广告*/
function closeAd(){
    $('.adv-gray').remove();
}
/*api文档页面*/
function apiFrame(_this,title){
    layer.open({
        type: 2,
        title:'<b><i class="st-book2"/>' + title + '</b>',
        area: ['1000px', '748px'],
        fixed: false, //不固定
        anim:1,
        btn:['关闭'],
        yes: function(index, layero){
            layer.close(index);
        },
        closeBtn:2,
        shade:0.6,
        shadeClose:true,
        resize:false,
        skin:'layui-layer-lan',
        content: '/apiFrame/go/detail'
    });
}