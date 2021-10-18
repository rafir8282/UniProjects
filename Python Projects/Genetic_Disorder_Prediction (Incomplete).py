# -*- coding: utf-8 -*-
"""
@Author: u3190029, u3191010

PRML Unit Project
Genetic Disorder Prediction
"""
###

subclass = {
        "Leber's hereditary optic neuropathy":"Mitochondrial genetic inheritance disorders",
        "Diabetes":"Multifactorial genetic inheritance disorders",
        "Leigh syndrome":"Mitochondrial genetic inheritance disorders",
        "Cancer":"Multifactorial genetic inheritance disorders",
        "Cystic fibrosis":"Single-gene inheritance diseases",
        "Tay-Sachs":"Single-gene inheritance diseases",
        "Hemochromatosis":"Single-gene inheritance diseases",
        "Mitochondrial myopathy":"Mitochondrial genetic inheritance disorders",
        "Alzheimer's":"Multifactorial genetic inheritance disorders"
        }

def mapping(value):
    for key, disorder in subclass.items():
        if value == key:
            return disorder

###

#### Imports
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split
#from sklearn.datasets import make_multilabel_classification
#from sklearn.multioutput import MultiOutputClassifier
from sklearn.tree import DecisionTreeClassifier

from sklearn import metrics
from sklearn.metrics import confusion_matrix
from sklearn.metrics import classification_report
# from scipy.stats import norm

#### Setting up
# Read Dataset CSV
genetics_data = pd.read_csv("genetic_disorders.csv", sep = ',')

# Explore Data
genetics_data.head()
genetics_data.shape
genetics_data.info()
genetics_data.corr()
genetics_data.select_dtypes(exclude = 'object').describe()
genetics_data.select_dtypes(include = 'object').describe()

# Our Target Variables
selected_columns = ['Genetic Disorder', 'Disorder Subclass']

#Unnecessary Columns list for dropping
unnecessary_columns = ['Patient First Name', 
                       'Family Name', 
                       "Father's name", 
                       'Institute Name', 
                       'Location of Institute', 
                       'Parental consent', 
                       'Place of birth', 
                       'Autopsy shows birth defect (if applicable)', 
                       'Status',
                       'Patient Age',
                       'Follow-up',
                       'Test 1',
                       'Test 2',
                       'Test 3',
                       'Test 4',
                       'Test 5'
                       ]

genetics_data = genetics_data.drop(unnecessary_columns, axis = 1)
genetics_data = genetics_data.reset_index(drop = True)

#####Work in progress#########
cols = genetics_data.columns
for x in cols:
    genetics_data[x].unique()                 
##############################
    
# Found some rows with NA for every column, remove them as they are useless
genetics_data.dropna(subset = ["Patient Id"], inplace = True)

# Drop NAs from Disorder Subclass
genetics_data = genetics_data.dropna(subset = ['Disorder Subclass'])
matchingTable = genetics_data[selected_columns].dropna().drop_duplicates()
#matchingTable = matchingTable.dropna()
#matchingTable = matchingTable.drop_duplicates()

#reset the index
genetics_data = genetics_data.reset_index(drop = True)

# for loop to iterate through replace disorder subclass based on the mapping function to subclass dictionary
for row in range(len(genetics_data['Disorder Subclass'])):
    genetics_data['Genetic Disorder'][row] = mapping(genetics_data['Disorder Subclass'][row])


# Check NAs
NA_percentage = genetics_data.isnull().sum() * 100 / len(genetics_data)
NA_df = pd.DataFrame({'NA Percentage': NA_percentage})

# Fill NA for Mother/Father Age by imputing median age
genetics_data["Mother's age"].fillna(genetics_data["Mother's age"].median(), inplace = True)
genetics_data["Father's age"].fillna(genetics_data["Father's age"].median(), inplace = True)

# Fill NA for previous abortion
genetics_data["No. of previous abortion"].fillna(genetics_data["No. of previous abortion"].mode()[0], inplace = True)

# Fill NA for symptoms 1 - 5
symptoms = ['Symptom 1', 'Symptom 2', 'Symptom 3', 'Symptom 4', 'Symptom 5']
genetics_data[symptoms] = genetics_data[symptoms].fillna(0)

# Fill NA for blood test result with inconclusive
genetics_data["Blood test result"] = genetics_data["Blood test result"].fillna("inconclusive")

# Fill NA for white blood cell count with mean
genetics_data["White Blood cell count (thousand per microliter)"].fillna(genetics_data["White Blood cell count (thousand per microliter)"].mean(), inplace = True)

# Fill NA for birth defects with None
genetics_data["Birth defects"] = genetics_data["Birth defects"].fillna("None")

# Fill NA for Assisted conception IVF/ART with No
genetics_data["Assisted conception IVF/ART"] = genetics_data["Assisted conception IVF/ART"].fillna("No")

# Fill NA for History of anomalies in previous pregnancies with Not Applicable, as it means no previous pregnancy
genetics_data["History of anomalies in previous pregnancies"] = genetics_data["History of anomalies in previous pregnancies"].fillna("Not Applicable")

# Fill NA for Respiratory Rate (breaths/min) with Normal, assumption made it would be normal as it would be one of the first things to be tested on a child
genetics_data["Respiratory Rate (breaths/min)"] = genetics_data["Respiratory Rate (breaths/min)"].fillna("Normal (30-60)")

# Fill NA for Heart Rate (rates/min with Normal, assumption made it would be normal as it would be one of the first things to be tested on a child
genetics_data["Heart Rate (rates/min"] = genetics_data["Heart Rate (rates/min"].fillna("Normal")

# Replace NA, No record and not available for No for consistency, with the assumption that it would be No
genetics_data["Birth asphyxia"].replace({"No record": "No", "Not available": "No", np.nan: "No"}, inplace = True)

# Fill NA for folic acid details with Not Applicable
genetics_data["Folic acid details (peri-conceptional)"] = genetics_data["Folic acid details (peri-conceptional)"].fillna("Not Applicable")

# Fill NA for Serious maternal illness with No as its assumed that if it is not recorded it would be No
genetics_data["H/O serious maternal illness"] = genetics_data["H/O serious maternal illness"].fillna("No")

# Replace Not applicable and "-" for No for consistency, with the assumption that it would be No
genetics_data["H/O radiation exposure (x-ray)"].replace({"Not applicable": "No", "-": "No"}, inplace = True)
genetics_data["H/O substance abuse"].replace({"Not applicable": "No", "-": "No"}, inplace = True)

# Count between combinations for maternal gene and Genes in mother's side
genetics_data[(genetics_data["Maternal gene"] == "Yes") & (genetics_data["Genes in mother's side"] == "Yes")].shape
genetics_data[(genetics_data["Maternal gene"] == "No") & (genetics_data["Genes in mother's side"] == "No")].shape
genetics_data[(genetics_data["Maternal gene"] == "Yes") & (genetics_data["Genes in mother's side"] == "No")].shape
genetics_data[(genetics_data["Maternal gene"] == "No") & (genetics_data["Genes in mother's side"] == "Yes")].shape
# Too much variance between the combinations, leave maternal gene for dropping NA

# Drop rest of NA 
genetics_data = genetics_data.dropna()

# Drop Patient Id
genetics_data = genetics_data.drop(['Patient Id'], axis = 1)

#### Data type wrangling

from sklearn import preprocessing

objects = genetics_data.select_dtypes(include = 'object')
floats =  genetics_data.select_dtypes(include = 'float64')

number = preprocessing.LabelEncoder()

for x in objects:
    genetics_data[x] = number.fit_transform(genetics_data[x])

#for x in objects:
#    genetics_data[x] = genetics_data[x].astype("category")
    
for x in floats:
    genetics_data[x] = pd.to_numeric(genetics_data[x])

nonInt = ["Blood cell count (mcL)", "White Blood cell count (thousand per microliter)"]    

for x in floats.drop(nonInt, axis = 1):
    genetics_data[x] = genetics_data[x].astype("int64")

# check the dtypes:
genetics_data.info()

#### Splitting
X = genetics_data.drop(columns = selected_columns)
y = genetics_data[selected_columns]

# Split into train test data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.25, random_state = 1)

X_train.select_dtypes(exclude = 'object').describe

# Look at individual values
y_train['Genetic Disorder'].unique()
y_train['Disorder Subclass'].unique()

y_trainTEST = y_train['Genetic Disorder']
y_TEST = y_test['Genetic Disorder']

y_trainTEST = y_train['Disorder Subclass']
y_TEST = y_test['Disorder Subclass']

#X_train, y_trainTEST = make_multilabel_classification(n_classes = 3, random_state = 0)
#y_train['Disorder Subclass'] = make_multilabel_classification(n_classes = 9, random_state = 0)
#### Modelling

# Decision Tree
model = DecisionTreeClassifier()
model.fit(X_train, y_trainTEST)
y_pred = model.predict(X_test)

#DTree = MultiOutputClassifier(DecisionTreeClassifier()).fit(X_train, y_trainTEST) 
#DTree.fit(X_train, y_trainTEST)
#y_pred = DTree.predict(X_test[-2:])

print("Accuracy:",metrics.accuracy_score(y_TEST, y_pred))
print("Precision:",metrics.precision_score(y_TEST, y_pred, average = 'weighted'))
print("Recall:", metrics.recall_score(y_TEST, y_pred, average = 'weighted'))
print("F1-score", metrics.f1_score(y_TEST, y_pred, average = 'weighted'))

# Random Forest
from sklearn.ensemble import RandomForestRegressor
rf = RandomForestRegressor(n_estimators = 1000, random_state = 42)
y_pred = rf.fit(X_train, y_trainTEST)

y_pred.score(X_test, y_TEST)




# Stuffing around
sns.heatmap(genetics_data.corr(), cmap = 'icefire')
sns.heatmap(X_train.corr(), cmap = 'icefire')
