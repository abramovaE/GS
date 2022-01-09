function createNewItem(answer, index){
    if (answer) {
        createItem(index);
    }
}

function createItem(index){
    document.activeElement.blur();
    document.getElementById('item' + index).value = '';
    document.getElementById('itemId' + index).value = '';
    document.getElementById('name' + index).value = '';
    window.open("add_item");
}

function addTr(c, index){
    if(c == 0) {
        const tr = document.getElementById("tr" + (index + 1));
        tr.hidden = false;
    }
}

function setMiddlePrice(globalItems, ean, index){
    globalItems.forEach(function(item){
        if(item.ean == ean){
            let itemIdCell = document.getElementById('itemId' + index);
            itemIdCell.value = item.id;
            let itemNameCell = document.getElementById('name' + index);
            itemNameCell.value = item.name;
        }
    });
}

function saveIncomeMain() {
    var isSubmit = 1;
    let incomeStrings = [];
    const table = document.getElementById('incomeStringTable');
    for (let index = 1; index < table.rows.length; index++) {
        let it = document.getElementById("itemId" + index);
        if(it != null) {
            let itemId = it.value;
            if (itemId.length > 0) {
                const count = document.getElementById("count" + index).value;
                const purPrice = document.getElementById("purPrice" + index).value;
                const purPriceAct = document.getElementById("purPriceAct" + index).value;
                const storeArticle = document.getElementById("storeArticle" + index).value;
                const batchNumber = document.getElementById("batchNumber" + index).value;
                if (count.length === 0) {
                    alert("Введите количество");
                    isSubmit = 0;
                    break;
                } else if (purPrice.length === 0) {
                    alert("Введите цену");
                    isSubmit = 0;
                    break;
                } else if (purPriceAct.length === 0) {
                    alert("Введите фактическую цену");
                    isSubmit = 0;
                    break;
                } else if (storeArticle.length === 0) {
                    alert("Введите артикул товара в магазине покупки");
                    isSubmit = 0;
                    break;
                } else if (batchNumber.length === 0) {
                    alert("Введите номер партии");
                    isSubmit = 0;
                    break;
                }
                const itemString = {};
                itemString.itemId = itemId;
                itemString.count = count;
                itemString.purPrice = purPrice;
                itemString.purPriceAct = purPriceAct;
                itemString.storeArticle = storeArticle;
                itemString.batchNumber = batchNumber;
                incomeStrings.push(itemString);
            }
        }
    }
    if (isSubmit === 1) {
        let incomeMain = document.getElementById('incomeMainForm');
        const incomeJson = document.createElement('input');
        incomeJson.name = "incomeJson";
        incomeJson.value = JSON.stringify(incomeStrings);
        incomeJson.hidden = true;
        incomeMain.appendChild(incomeJson);
        incomeMain.submit();
    }
    return isSubmit === 1;
}

function incrementCount(table, inputItem, index){
    for(let j = 1; j < table.rows.length; j++) {
        let item = document.getElementById("item" + j)
        if (item != null) {
            let itemId = item.value;
            if (itemId === inputItem) {
                let countCell = document.getElementById("count" + j);
                countCell.value = Number(countCell.value) + 1
                if (countCell.value > 1) {
                    document.getElementById('item' + index).value = "";
                    document.getElementById('itemId' + index).value = '';
                    document.getElementById('name' + index).value = '';
                    return 1;
                }
            }
        }
    }
    return 0;
}

function addIncomeString() {
    const id = 'incomeStringTable';
    const table = document.getElementById(id);
    let rowIndex = table.rows.length;
    const tableHeader = document.getElementById('incomeStringTableHeader');
    tableHeader.hidden = false;
    const tr = document.getElementById('tr' + rowIndex);
    tr.closest('tr' + rowIndex)
}