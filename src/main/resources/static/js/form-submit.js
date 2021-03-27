 Income income = "{"salary" : 0.00, "bonus" : 0.00, "other_income" : "0.00"}"
  function handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    Income income = (Income) Object.fromEntries(data.entries());
    console.log({ value });
    userAction(value);
  }
<!--  const form = document.querySelector('form');-->
  document.forms[0].addEventListener('submit', handleSubmit);

  function userAction(income) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
         }
    };
<!--    xhttp.open("POST", "/income/2020-21");-->
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(income);
}