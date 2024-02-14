const inputSearchName = document.getElementById('searchName');
console.log(searchName);
const pickup = document.getElementById('pickup');

function getEmployeeName(event) {

    const elm = document.getElementsByClassName('pickup');

    if (0 < elm.length) {
        while (elm.length) {
            elm.item(0).remove()
        }
    }

    let searchNameValue = event.target.value;

    let url = "http://localhost:8080/api/employees?name="+searchNameValue;
    fetch(url)
    .then(response => {
        return response.json();
    })
    .then(data => {
        if (data.length>0) {
            
            data.forEach(element => {
            let option = document.createElement('option');
            option.textContent = element.name;
            option.classList.add('pickup');
            pickup.append(option);
            });
        }else{
            let option = document.createElement('option');
            option.textContent = "候補者なし";
            option.classList.add('pickup');
            pickup.append(option);
        }
    })
    .catch(error => {
        console.log("失敗しました");
    });
}
function selectPickup(event) {
  let name = event.target.value;
  inputSearchName.value = name;
}
inputSearchName.addEventListener('input',getEmployeeName)
pickup.addEventListener('change',selectPickup)
