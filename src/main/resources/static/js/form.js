
const zipCode = document.getElementById('zipCode');
console.log(zipCode);
const address = document.getElementById('address');
address.textContent = "555";
console.log(address.textContent);

function getAddress(event) {

    let zipCodeValue = event.target.value;
    let zipCodeLength = zipCodeValue.length;
    if (zipCodeLength==7) {
        
        let url = "https://zipcloud.ibsnet.co.jp/api/search?zipcode="+zipCodeValue;
        fetch(url)
        .then(response => {
            return response.json();
        })
        .then(data => {
            let arrayAddres = data.results[0];
            let textAddress = arrayAddres['address1']+arrayAddres['address2']+arrayAddres['address3'];
          
            address.value = textAddress;
            
        })
        .catch(error => {
            console.log("失敗しました");
        });
    }
}
zipCode.addEventListener('input',getAddress)
// function getAddress(event){
//     let zipCodeValue = event.target.value;
//     let zipCodeLength = zipCodeValue.length;
//     if (zipCodeLength==7) {
//         axios({
//             method: 'get',
//             url: 'https://zipcoda.net/api',
//             data: {data:zipCodeValue}
//         }).then(function(response) {
//             let arrayAddres = data.results[0];
//             let textAddress = arrayAddres['address1']+arrayAddres['address2']+arrayAddres['address3'];
//             console.log(textAddress);
//             // address.value = textAddress;
//         }).catch(function(error) {
//             console.log("失敗しました");
//         })
//     }
// }

// zipCode.addEventListener('input',getAddress)
