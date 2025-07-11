const stripe = Stripe('pk_test_51RX1bvCv427D6dK1M5DoW6FituV6iEjJc1VHoei8J4tXdqu89CXJLEN72DoaXFXuJzjig4AJ5ZiGynL56Nodvcha00gYgczh9H');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
 stripe.redirectToCheckout({
   sessionId: sessionId
 })
});