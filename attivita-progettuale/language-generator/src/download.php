<?php
session_start();

$files = [
    1 => "hints",
    2 => "log",
    3 => "flag",
];

if (!isset($_GET["id"])) {
    exit("It's time to tackle the productions");
}

$id = (int) $_GET["id"];
if (!array_key_exists($id, $files)) {
    exit("It's time to tackle the productions");
}

$filePath = $files[$id];
if (
    isset($_SESSION[$filePath]) &&
    ($id != 2 || $_SESSION["show_log"] === true)
) {
    header("Content-Type: text/plain");
    header("Content-Disposition: inline");

    echo $_SESSION[$filePath];

    exit();
} else {
    exit("It's time to tackle the productions");
}
?>
