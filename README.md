1. Note, that regexp in url query should be encoded, for example:
   product?nameFilter=^.*[eva].*$ -> product?nameFilter=%5E.%2A%5Beva%5D.%2A%24
2. Filter, that returns all entries, for example may be:
   ?nameFilter=""
