let addToCartButtons = document.querySelectorAll(".addToCart")

addToCartButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        let cartService = Java.type('com.codecool.shop.service.CartService')
        console.log(btn.value)
    })
})