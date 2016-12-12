<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script>
      jQuery(document).ready(function($){
          var $timeline_block = $('.cd-timeline-block');

          //hide timeline blocks which are outside the viewport
          $timeline_block.each(function(){
              if($(this).offset().top > $(window).scrollTop()+$(window).height()*0.75) {
                  $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
              }
          });

          //on scolling, show/animate timeline blocks when enter the viewport
          $(window).on('scroll', function(){
              $timeline_block.each(function(){
                  if( $(this).offset().top <= $(window).scrollTop()+$(window).height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
                      $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
                  }
              });
          });
      });
  </script>
</head>
<body>
<header>
  <h1>Responsive Vertical Timeline</h1>
</header>

<section id="cd-timeline" class="cd-container">
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-picture">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-picture.svg" alt="Picture">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Title of section 1</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus veritatis qui ut.</p>
      <a href="#0" class="cd-read-more">Read more</a>
      <span class="cd-date">Jan 14</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-movie">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-movie.svg" alt="Movie">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Title of section 2</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde?</p>
      <a href="#0" class="cd-read-more">Read more</a>
      <span class="cd-date">Jan 18</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-picture">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-picture.svg" alt="Picture">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Title of section 3</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Excepturi, obcaecati, quisquam id molestias eaque asperiores voluptatibus cupiditate error assumenda delectus odit similique earum voluptatem doloremque dolorem ipsam quae rerum quis. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus quia eos repellat consequuntur perferendis enim amet quae quasi repudiandae sed quod veniam dolore possimus rem voluptatum eveniet eligendi quis fugiat aliquam sunt similique aut adipisci.</p>
      <a href="#0" class="cd-read-more">Read more</a>
      <span class="cd-date">Jan 24</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-location">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-location.svg" alt="Location">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Title of section 4</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus veritatis qui ut.</p>
      <a href="#0" class="cd-read-more">Read more</a>
      <span class="cd-date">Feb 14</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-location">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-location.svg" alt="Location">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Title of section 5</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum.</p>
      <a href="#0" class="cd-read-more">Read more</a>
      <span class="cd-date">Feb 18</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-movie">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/148866/cd-icon-movie.svg" alt="Movie">
    </div> <!-- cd-timeline-img -->
    
    <div class="cd-timeline-content">
      <h2>Final Section</h2>
      <p>This is the content of the last section</p>
      <span class="cd-date">Feb 26</span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
</section> <!-- cd-timeline -->
</body>
</html>
