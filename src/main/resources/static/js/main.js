document.addEventListener('DOMContentLoaded', () => {

  // Get all "navbar-burger" elements
  const $navbarBurgers = Array.prototype.slice.call(
      document.querySelectorAll('.navbar-burger'), 0);

  // Check if there are any navbar burgers
  if ($navbarBurgers.length > 0) {

    // Add a click event on each of them
    $navbarBurgers.forEach(el => {
      el.addEventListener('click', () => {

        // Get the target from the "data-target" attribute
        const target = el.dataset.target;
        const $target = document.getElementById(target);

        // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
        el.classList.toggle('is-active');
        $target.classList.toggle('is-active');

      });
    });
  }

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

// ready(function () {
//   bulmaCarousel.attach('#slider', {
//     slidesToScroll: 1,
//     slidesToShow: 4,
//   });
//   bulmaCarousel.attach('#slider1', {
//     slidesToScroll: 1,
//     slidesToShow: 3,
//     infinite: true
//   });
// });