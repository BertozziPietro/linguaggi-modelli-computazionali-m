FROM php:8.2-apache@sha256:42de06c4cdff50b81b34112c700585ad1b194c961cb0e887a6906c7824223e23

# Imposta la cartella di lavoro all'interno del contenitore
WORKDIR /var/www/html

# Copia i file del progetto nel contenitore
COPY index.html grammar_generator.php download.php style.css validation.js ./
