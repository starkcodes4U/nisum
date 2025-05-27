// Inventory Management System JavaScript

// Handle Add Inventory Form Submission
document.getElementById("addInventoryForm").addEventListener("submit", function (e) {
    e.preventDefault();
    // Build URL-encoded form data
    const params = new URLSearchParams();
    new FormData(this).forEach((value, key) => params.append(key, value));

    // Debug: Log form data to console
    console.log("Form data being submitted:");
    for (let pair of params.entries()) {
        console.log(pair[0] + ": " + pair[1]);
    }

    fetch("/Mini-Project-Inventory-Management/AddInventoryServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8" },
        body: params.toString()
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            if (data.success) {
                loadInventory();
                document.getElementById("addInventoryForm").reset();
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error submitting form. Check console for details.");
        });
});

// Load Inventory Data Function
function loadInventory() {
    fetch("/Mini-Project-Inventory-Management/GetInventoryServlet")
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#inventoryTable tbody");
            tbody.innerHTML = "";
            data.forEach(row => {
                tbody.innerHTML += `<tr>
                <td>${row.SKU}</td>
                <td>${row.Location}</td>
                <td>${row.Quantity}</td>
                <td>${row.ReservedQty}</td>
                <td>${row.AllocatedQty}</td>
            </tr>`;
            });
        })
        .catch(error => {
            console.error("Error loading inventory:", error);
        });
}

// Add event listener to the action selection dropdown
document.getElementById("action").addEventListener("change", function() {
    const action = this.value;
    const orderField = document.getElementById("orderId");

    // Make Order ID required only for Reserve Inventory action
    if (action === "reserveInventory") {
        orderField.setAttribute("required", "");
        orderField.style.display = "block";
    } else {
        orderField.removeAttribute("required");
        // Hide Order ID field only for Adjust, show for Cancel, Reserve, and Allocate
        if (action === "adjustInventory") {
            orderField.style.display = "none";
        } else {
            orderField.style.display = "block";
        }
    }
});

// Handle Inventory Action Form Submission
document.getElementById("inventoryActionsForm").addEventListener("submit", function (e) {
    e.preventDefault();
    const action = document.getElementById("action").value;
    const actionMessage = document.getElementById("actionMessage");

    // Build URL-encoded form data
    const params = new URLSearchParams();
    new FormData(this).forEach((value, key) => params.append(key, value));

    // Log form data to console
    console.log("Action form data being submitted:");
    for (let pair of params.entries()) {
        console.log(pair[0] + ": " + pair[1]);
    }

    fetch("/Mini-Project-Inventory-Management/InventoryActionServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8" },
        body: params.toString()
    })
    .then(res => res.json())
    .then(data => {
        actionMessage.textContent = data.message;
        actionMessage.className = data.success ? "message-box success" : "message-box error";

        if (data.success) {
            loadInventory();
            if (action !== "adjustInventory") { // Don't reset the form for adjust inventory to allow multiple adjustments
                document.getElementById("inventoryActionsForm").reset();
                document.getElementById("action").dispatchEvent(new Event("change"));
            }
        }
    })
    .catch(error => {
        console.error("Error:", error);
        actionMessage.textContent = "Error submitting form. Check console for details.";
        actionMessage.className = "message-box error";
    });
});

// Initialize on page load
window.onload = function() {
    loadInventory();
    // Initialize action form field visibility
    document.getElementById("action").dispatchEvent(new Event("change"));
};
