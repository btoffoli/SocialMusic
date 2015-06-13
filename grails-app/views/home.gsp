<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
    <meta name="layout" content="main"/>
    <title>Social Music</title>
    <meta name="description" content="free website template" />
    <meta name="keywords" content="enter your keywords here" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easing.min.js"></script>
    <script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
    <script type="text/javascript">
        $(window).load(function() {
            $('#slider').nivoSlider();
        });
    </script>
    <g:javascript src="home/home.js"/>
    <g:javascript src="youtube.js"/>
</head>

<body>
<div id="site_content">

    <div id="banner_image">
        <div id="slider-wrapper">
            <div id="slider" class="nivoSlider">
                <img src="images/home_1.jpg" alt="All your music, one place." />
                <img src="images/home_2.jpg" alt="" />
            </div><!--close slider-->
        </div><!--close slider_wrapper-->
    </div><!--close banner_image-->

    <div class="sidebar_container">
        <div id="lastAddedAlbum" class="sidebar" style="display:none" >
            <div class="sidebar_item">
                <h2><g:message code="default.button.home.Welcome"></g:message></h2>
                <p>Tenha sempre seu artista favorito aonde você estiver.</p>
            </div><!--close sidebar_item-->
        </div><!--close sidebar-->
        <div id="lastAddedAuthorship" class="sidebar" style="display:none" >
            <div class="sidebar_item">
                <h2><g:message code="default.button.home.LastUpdate"></g:message></h2>
                <h4>Abril de 2015</h4>

            </div><!--close sidebar_item-->
        </div><!--close sidebar-->
        <div class="sidebar">
            <div class="sidebar_item">
                <br><br><br><br>
                <p>Projeto de site feito para a disciplina de DWWS do professor Vitor Souza. Autores: Bruno Toffoli, Victor Lemos e Fábio Oliveira</p>
            </div><!--close sidebar_item-->
        </div><!--close sidebar-->
    </div><!--close sidebar_container-->

    <div id="content">
        <div class="content_item" style="text-align:center" >
            <div id="lastAddedMusic" style="display:none" >
                <h1 style="background: -webkit-linear-gradient(#C56B85, #381029);border-radius: 0px 15px 0px 15px;padding: 20px;" >Última Música</h1>
                <div id="mainPlayer"></div>
            </div>
            <!--
            <h1>Mais rock, ou mais pop ?</h1>
            <div class="content_container">
                <p>Você não precisa mais manter seu player aberto. Alias, em tempos de streaming, para que manter offline quando você pode ter seu acervo músical com você, aonde estiver ?</p>
                <div class="button_small">
                    <a href="http://codigofonte.uol.com.br/noticias/pesquisa-streaming-esta-acabando-com-pirataria-no-brasil" target="_blank">Saiba mais</a>
                </div>
            </div>
            -->
        </div>
    </div>

    <!--
    <div id="content_blue">
        <div class="content_blue_container_box">
            <h4>SocialMusic no Facebook</h4>
            <p> Veja o que estão falando sobre o nosso trabalho</p>
            <div class="readmore">
                <a href="#"><g:message code="default.button.home.VisitUs"></g:message></a>
            </div>
        </div>
    </div>
    -->
</div><!--close site_content-->
</body>
</html>
