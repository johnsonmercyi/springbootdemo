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
  sale: {
    customerId: '12345',
    saleTotal: '2000'
  },
  saleItems: [
    { productId: 'egg_id', qty: 5, total: 2000 },
    { productId: 'milk_id', qty: 2, total: 500 },
    { productId: 'onions_id', qty: 4, total: 1500 },
  ]
}








// Request payload

{
  "sale": {
    "customerId": "6044bf6d-ccf3-4ae2-a96b-2d54c3a588e9",
      "saleTotal": 550
  },
  "saleItems": [
    { "productId": "7b7a468a-bbf2-4bf2-939e-72eb5c70dcbd", "qty": 1, "total": 150, "sellerId": "87d02d31-c90a-4f82-8c73-6ed01f1e8877" },
    { "productId": "56af4330-8cbd-47a1-b47f-fbff2e19b1a0", "qty": 1, "total": 400, "sellerId": "87d02d31-c90a-4f82-8c73-6ed01f1e8877" }
  ]
}











// Response

[
  {
    "id": "b9f208b0-d17c-451a-98a8-937aa6cfd818",
    "sale": {
      "id": "1a7a1e90-f16e-42b5-8ce0-59ba51a280c9",
      "customer": {
        "customerId": "6044bf6d-ccf3-4ae2-a96b-2d54c3a588e9",
        "customerName": "Jane Doe",
        "customerEmail": "johndoe@email.com"
      },
      "saleTotal": 150.0,
      "created": null,
      "updated": null
    },
    "product": {
      "id": "7b7a468a-bbf2-4bf2-939e-72eb5c70dcbd",
      "category": {
        "id": "fd0e8764-52e8-4bb8-ae9b-92c1a5aca810",
        "name": "well-beings"
      },
      "name": "Colgate Tooth Brush",
      "cost": 800.0,
      "price": 950.0,
      "refNo": "1a35e9bf-e3cd-46fc-8385-e1ce6ab5f49b"
    },
    "seller": {
      "id": "87d02d31-c90a-4f82-8c73-6ed01f1e8877",
      "name": "Mark Seller 1",
      "email": "markseller_1@email.com"
    },
    "quantity": 1,
    "total": 150.0,
    "created": null,
    "updated": null
  },
  {
    "id": "31a202af-fed9-450d-9fcd-3c2b142d02d1",
    "sale": {
      "id": "1a7a1e90-f16e-42b5-8ce0-59ba51a280c9",
      "customer": {
        "customerId": "6044bf6d-ccf3-4ae2-a96b-2d54c3a588e9",
        "customerName": "Jane Doe",
        "customerEmail": "johndoe@email.com"
      },
      "saleTotal": 400.0,
      "created": null,
      "updated": null
    },
    "product": {
      "id": "56af4330-8cbd-47a1-b47f-fbff2e19b1a0",
      "category": {
        "id": "fd0e8764-52e8-4bb8-ae9b-92c1a5aca810",
        "name": "well-beings"
      },
      "name": "Colgate Tooth Paste",
      "cost": 800.0,
      "price": 950.0,
      "refNo": "68900dd6-63ba-4f23-b7f4-f9263980448f"
    },
    "seller": {
      "id": "87d02d31-c90a-4f82-8c73-6ed01f1e8877",
      "name": "Mark Seller 1",
      "email": "markseller_1@email.com"
    },
    "quantity": 1,
    "total": 400.0,
    "created": null,
    "updated": null
  }
]