/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
$(document).ready(function (){
    $('#story-pic').change(function(){
        const file = this.files[0];
        console.log(file);
        if (file){
          let reader = new FileReader();
          reader.onload = function(event){
            console.log(event.target.result);
            $('#output').attr('src', event.target.result);
          };
          reader.readAsDataURL(file);
        }
      });
      
    $('#item-pic').change(function(){
      const file = this.files[0];
      console.log(file);
      if (file){
        let reader = new FileReader();
        reader.onload = function(event){
          console.log(event.target.result);
          $('#output1').attr('src', event.target.result);
        };
        reader.readAsDataURL(file);
      }
    });
    
    $('#img-output2').change(function(){
      const file = this.files[0];
      console.log(file);
      if (file){
        let reader = new FileReader();
        reader.onload = function(event){
          console.log(event.target.result);
          $('#output2').attr('src', event.target.result);
        };
        reader.readAsDataURL(file);
      }
    });
    
    $('#img-output3').change(function(){
      const file = this.files[0];
      console.log(file);
      if (file){
        let reader = new FileReader();
        reader.onload = function(event){
          console.log(event.target.result);
          $('#output3').attr('src', event.target.result);
        };
        reader.readAsDataURL(file);
      }
    });
    
    var tabLinks = document.querySelectorAll(".tab");
    var tabContent = document.querySelectorAll(".tab-item");

    tabLinks.forEach(function(el) {
       el.addEventListener("click", openTabs);
    });


    function openTabs(el) {
       var btn = el.currentTarget;
       var post = btn.dataset.post; 

       tabContent.forEach(function(el) {
          el.classList.remove("active");
       }); 

       tabLinks.forEach(function(el) {
          el.classList.remove("active");
       }); 

       document.querySelector("#" + post).classList.add("active");
       
       btn.classList.add("active");      
    }
    
    $(window).scroll(function () {
            if ($(this).scrollTop() > 50) {
                    $('#back-to-top').fadeIn();
            } else {
                    $('#back-to-top').fadeOut();
            }
    });
    // scroll body to 0px on click
    $('#back-to-top').click(function () {
            $('body,html').animate({
                    scrollTop: 0
            }, 400);
            return false;
    });
});
