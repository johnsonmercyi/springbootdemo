document.addEventListener('DOMContentLoaded', function(event) {
  console.log("Content loaded!");
  localStorage.setItem("cart", JSON.stringify({
    items: [

    ]
  }));

  const addToCart = document.querySelector("#addToCart");
  const item = document.querySelector("#item");
  const cart = JSON.parse(localStorage.getItem("cart"));

  addToCart.addEventListener('click', () => {
    if (!item.value) {
      alert("Please enter an item to add to cart!");
    } else {
      const item = {
        product: "wlgcduiawsdgcuidc",
        qty: 12,
        total: 5000
      }
      cart.items.push(item);

      // Print the cart!
      console.log("OUR CART: ", cart);
    }
  });

});













const payload = {
  sales: {
    customer_id: '12345',
    seller_id: '678910'
  },
  saleItems: [
    { product: 'egg_id', qty: 5, total: 2000 },
    { product: 'milk_id', qty: 2, total: 500 },
    { product: 'onions_id', qty: 4, total: 1500 },
  ]
}