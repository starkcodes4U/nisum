function checkPalindrome() {
  const input = document.getElementById("inputText").value.toLowerCase().replace(/[^a-z0-9]/g, '');
  const reversed = input.split('').reverse().join('');
  const result = document.getElementById("result");

  if (input === "") {
    result.textContent = "Please enter a word.";
    result.style.color = "orange";
  } else if (input === reversed) {
    result.textContent = "Yes, it's a palindrome!";
    result.style.color = "green";
  } else {
    result.textContent = "No, it's not a palindrome.";
    result.style.color = "red";
  }
}
