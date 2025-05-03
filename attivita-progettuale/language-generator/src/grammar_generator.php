<?php
session_start();

if (!isset($_SESSION["traversal_method"])) {
    $_SESSION["traversal_method"] = "dfs";
}

if (!isset($_SESSION["log"])) {
    $_SESSION["log"] = sprintf(
        "%-20s\t%-20s\t%-10s\n",
        "Language",
        "FoundPhrase",
        "Traversal"
    );
}

function appendToLog($languageName, $phrase, $traversal_method)
{
    $logEntry = sprintf(
        "%-20s\t%-20s\t%-10s\n",
        $languageName,
        $phrase,
        $traversal_method
    );

    $_SESSION["log"] = $_SESSION["log"] . $logEntry;
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $languageName = isset($_POST["languageName"])
        ? trim($_POST["languageName"])
        : "";
    $productions = [];
    foreach ($_POST as $key => $value) {
        if (preg_match('/^[A-Z]$/', $key) && $value === "on") {
            $productions[] = $key;
        }
    }
    if (empty($languageName) || count($productions) === 0) {
        header("Location: index.html");
        exit();
    }

    $hint1 = "Please be kind and ask politely";
    $hint2 = "\nIf only you could convince me to show you the log...";
    $hint3 =
        "\nI understood what you had in mind and decided to add L and V to your productions";

    $log_cleared = sprintf(
        "%s\n%-20s\t%-20s\t%-10s\n",
        "The log has been cleared successfully",
        "Language",
        "FoundPhrase",
        "Traversal"
    );

    $_SESSION["show_log"] = 0; // do not show log anymore

    if ($productions[0] === "R") {
        $_SESSION["hints"] = $hint1;
        appendToLog($languageName, "hint", $_SESSION["traversal_method"]);
    } elseif (
        $productions[0] === "A" &&
        in_array("H", $productions) &&
        in_array("L", $productions) &&
        in_array("S", $productions) &&
        in_array("W", $productions)
    ) {
        $_SESSION["hints"] = $hint1 . $hint2;
        appendToLog($languageName, "pleasehint", $_SESSION["traversal_method"]);
    } elseif (
        $productions[0] === "B" &&
        in_array("G", $productions) &&
        in_array("H", $productions) &&
        in_array("K", $productions) &&
        in_array("W", $productions)
    ) {
        $_SESSION["hints"] = $hint1 . $hint2 . $hint3;

        $_SESSION["show_log"] = true;
        $_SESSION["log"] = $log_cleared; // reset log
        appendToLog($languageName, "clearlog", $_SESSION["traversal_method"]);
        appendToLog($languageName, "showlog", $_SESSION["traversal_method"]);
    } elseif (
        $productions[0] === "O" &&
        in_array("G", $productions) &&
        in_array("I", $productions) &&
        in_array("M", $productions) &&
        in_array("Q", $productions) &&
        in_array("S", $productions)
    ) {
        $_SESSION["show_log"] = true;
        appendToLog($languageName, "showlog", $_SESSION["traversal_method"]);
    } elseif (
        $productions[0] === "N" &&
        in_array("G", $productions) &&
        in_array("O", $productions) &&
        in_array("T", $productions) &&
        in_array("U", $productions) &&
        in_array("V", $productions)
    ) {
        $_SESSION["traversal_method"] = "bfs";
        appendToLog(
            $languageName,
            "changetraversal",
            $_SESSION["traversal_method"]
        );
    } elseif (
        $_SESSION["traversal_method"] === "bfs" &&
        $productions[0] === "Y" &&
        in_array("D", $productions) &&
        in_array("G", $productions) &&
        in_array("P", $productions) &&
        in_array("Q", $productions) &&
        in_array("A", $productions)
    ) {
        $_SESSION["flag"] = getenv("FLAG");
        appendToLog($languageName, "getflag", $_SESSION["traversal_method"]);
    } else {
        appendToLog($languageName, "nothing", $_SESSION["traversal_method"]);
    }

    header("Location: index.html");
    exit();
}
?>
