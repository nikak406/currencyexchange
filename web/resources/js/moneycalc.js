function myHandler(){
    var rate = document.getElementById("j_idt26:0:rate").innerHTML;
    var amount = document.getElementById("j_idt51:amount").value;
    var result = document.getElementById("j_idt51:result");
    var mult = amount * rate;
    if (mult > 0){
        result.innerHTML = (amount * rate) + " UAH";
    } else{
        result.innerHTML = "";
    }
}
