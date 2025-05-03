function validate(event) {
  event.preventDefault();
  const name = document.getElementById("languageName").value;
  const checkboxes = document.querySelectorAll("input[type='checkbox']");
  let selectedCount = 0;
  checkboxes.forEach(function (checkbox) {
    if (checkbox.checked) selectedCount++;
  });
  if (name.trim() === "" || selectedCount === 0 || selectedCount > 5) {
    alert("Name the language and use 0 to 5 productions");
    return false;
  }
  document.getElementById("grammarForm").submit();
  return true;
}
