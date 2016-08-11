# ROP-data-analysis
Examining ROP data.

There is a main class with the project title that deals with file parsing and the interface. The Population class constructs populations out of the given data for faster/more logical retrieval and better organization. The main class and the Population class relate primarily in the form of a hashmap created in the main class that stores every Population object. The reason a hashmap was used was for dynamic yet organized object creation as well as clean retreavel of objects when needed. Also note the unse of two dimentional arrays to store the population 'multi'. I am considering storing a copy of the multi inside of the Population object too. My only reserve is that the Population objects are created at the time of file parsing (by the file parser, actually), so the number of objects can get very high, therefore they should be kept lean. 

Data is entered in the following format:
```
p_1:DATA,p_2:DATA,n:DATA,OR:DATA
p_1:DATA,p_2:DATA,n:DATA,OR:DATA
... more lines of data with the same format ...
```
replace DATA with desired DATA. The file parser is not very flexible and will throw and error if the data is entered incorrectly. Also note that n must be and integer. Data is read into the program line by line to keep things lean. Each line of data will generate a Population object.

Program requires libraries  
 java.math.RoundingMode;
 java.text.DecimalFormat;
 java.util.*;
 java.io.*;
 
 
