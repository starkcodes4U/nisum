function addItem() {
  const input = document.getElementById("itemInput");
  const itemText = input.value.trim();

  if (itemText !== "") {
    const li = document.createElement("li");
    li.textContent = itemText;
    document.getElementById("itemList").appendChild(li);
    input.value = "";
  } else {
    alert("Please enter a valid item.");
  }
}
