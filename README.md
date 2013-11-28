Ćwiczenie: dodaj funckjonalność do istniejącego kodu
****************************************************

* Kodowanie: ISO-8859-2 (prawie)

* Query String
```
	String urlString = "http://portalwiedzy.onet.pl/tlumacz.html?qs="
		+ wordToFind + "&tr=ang-auto&x=0&y=0";
```	
	
* Wyrażenie regularne #1: wyłapuje oryginalne angielskie słowa oraz fragment 
z zestawieniem tłumaczeń
```
	Pattern pat = Pattern.compile(".*?<div class=a2b style=\"padding: "
		+ "0px 0 1px 0px\">\\s?" + "(.+?)&nbsp;"
		+ ".+?<BR>(.*?)</div>.*?"
		);
```
	
* Wyrażenie regularne #2: pobiera poszczególne elementy tłumaczenia
```
	Pattern pattern = Pattern.compile("(<B>\\d+</B>\\s)?(.*?)<BR>");
```
	
* Przykładowe wywołanie fragmentu z tłumaczeniami
```
	extractTranslation(englishWord, polishHtmlFragment + "<BR>")
```
