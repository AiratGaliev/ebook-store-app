document.addEventListener('DOMContentLoaded', () => {

  bulmaCarousel.attach('#slider', {
    slidesToScroll: 1,
    slidesToShow: 4,
  });
  bulmaCarousel.attach('#slider1', {
    slidesToScroll: 1,
    slidesToShow: 3,
    infinite: true
  });

  bulmaCarousel.attach('#slider2', {
    slidesToScroll: 1,
    slidesToShow: 6,
    loop: true
  });

  bulmaCarousel.attach('.hero-carousel', {
    slidesToScroll: 1,
    slidesToShow: 1,
    pagination: false,
    duration: 600,
    effect: 'fade',
    autoplay: true,
    autoplaySpeed: 5000,
    pauseOnHover: true,
    loop: true
  });
});