import pandas as pd
import scipy.stats as scpy

# OSCAR: I think, overall, it is easier to process data with data frames:
# read data from the excel files into DFs, then processing it to get results as more DFs, and then writing this DFs
# to either an excel or CSV file
# RESOURCES:
# https://www.geeksforgeeks.org/python-pandas-dataframe/
# https://pandas.pydata.org/docs/user_guide/indexing.html

###############################################
#   Interact with correlation_analysis.xlsx   #
###############################################

# Returns the dataframe DONE
def readCorrelationAnalysis():
    return pd.read_excel('data/correlation_analysis.xlsx')

def writeCorrelationAnalysis(correlationAnalysisDF):
    correlationAnalysisDF.to_excel('data/correlation_analysis.xlsx', engine='xlsxwriter', index=False)

# For Reference: The columns 'Complexity Metric' and '# of snippets judged (complexity)' are added manually.

# Sets the values of the column '# of snippets with warnings' in the correlation analysis dataframe. DONE
# Returns the modified correlation analysis dataframe.
def setNumSnippetsWithWarningsColumn(dfListAnalysisTools, correlationAnalysisDF):
    datasets = correlationAnalysisDF.iloc[:, 0]  # A list of all datasets being used

    # A count of the number of snippets that contain warnings for each dataset
    countSnippetsPerDataset = sortUniqueSnippetsByDataset(datasets, getSnippetsWithWarnings(dfListAnalysisTools))

    correlationAnalysisDF.iloc[:, 2] = list(countSnippetsPerDataset.values())

    return correlationAnalysisDF

# Sets the values of the column '# of datapoints of correlation' in the correlation analysis dataframe. DONE
# Returns the modified correlation analysis dataframe.
def setNumDatapointsForCorrelationColumn(dfListCorrelationDatapoints, correlationAnalysisDF):
    # Loop through each set of datapoints, there is one for each study
    for i, df in enumerate(dfListCorrelationDatapoints):
        numDataPoints = len(df)

        correlationAnalysisDF.iloc[i, 3] = numDataPoints

    return correlationAnalysisDF

# Sets the values of the column 'Kendall's Tau' in the correlation analysis dataframe. DONE
# Returns the modified correlation analysis dataframe.
def setKendallTauColumn(kendallTauVals, correlationAnalysisDF):
    correlationAnalysisDF.iloc[:, 4] = kendallTauVals

    return correlationAnalysisDF

##############################
#   Setup Correlation Data   #
##############################

# Master function for compiling the datapoints for correlation. DONE ISH
# Returns a list of dataframes where each dataframe contains the datapoints (x = complexity metric, y = # of warnings) for a specific dataset.
def setupCorrelationData(dfListAnalysisTools, correlationAnalysisDF):
    # Datapoint structure as a dictionary, each row represents a snippet in numerical order -> first row for first snippet etc. excluding headers
    data = {
        'Metric': [],
        'Warning Count': []
    }
    dfListCorrelationDatapoints = []

    warningsPerSnippetPerDataset = getNumWarningsPerSnippetPerDataset(dfListAnalysisTools, correlationAnalysisDF)

    # Compile datapoints for the COG Dataset 3 Study
    dfListCorrelationDatapoints.append(setCogDataset3Datapoints(warningsPerSnippetPerDataset['COG Dataset 3'], data))

    # TODO Add more datasets here...

    return dfListCorrelationDatapoints

# Gets a list of complexity metrics and a list of warning counts for each snippet in COG Dataset 3. DONE
# Adds that data to a dictionary that is then converted to a dataframe.
def setCogDataset3Datapoints(warningsPerSnippet, data):
    data['Metric'] = readCOGDataset3StudyMetrics()
    data['Warning Count'] = warningsPerSnippet

    return pd.DataFrame(data)

    # possible dataframe syntax (not sure)
    # df = read_excel_file(...)
    # df[:5]
    # x = df["Dataset"]

# TODO: functions for future datasets go here

##################################
#   Retrieve Data From Studies   #
##################################

# Reads the results of the cog data set 3 study. It contains 120 people who rated 100 snippets on a scale of 1-5.
# 1 being less readable and 5 being more readable.
# TODO:
# OSCAR: where are we filtering out the 4 snippets that are commented out?
# OSCAR: in cog_dataset_3.csv, are the snippets identified by column index?
def readCOGDataset3StudyMetrics():
    df = pd.read_csv('data/cog_dataset_3.csv')

    # Returns a list of the averages for each snippet
    return [round(sum(df[column]) / len(df[column]), 2) for column in df.columns[2:]]

###############################################
#   Retrieve Data From Analysis Tool Output   #
###############################################

# Reads in warning per snippet data from each analysis tool.
# There is data for one analysis tool per csv file.
# The data frames for each file are returned in a list.
def readAnalysisToolOutput():
    dfList = []

    #TODO: change from excel to csv file
    dfList.append(pd.read_csv('data/checker_framework_data.csv'))

    #TODO: Add more analysis tool output here:

    return dfList

# Read all the data output data frames from the various analysis tool 
# and create a list of all the unique snippets across all the datasets that contain warnings
def getSnippetsWithWarnings(dfListAnalysisTools):
    uniqueSnippets = []

    for df in dfListAnalysisTools:
        listSnippets = df['Snippet'].to_list()
        uniqueSnippets.extend(list(set(listSnippets)))

    # Name of snippets in 'uniqueSnippets' format example: COG Dataset 1 - 12
    #                                              format: Dataset Name - Snippet #
    return uniqueSnippets

# Gets a count of the number of snippets that contain warnings for each dataset
# Returns a dictionary where the keys is the names of data sets. The values are an integer count of 
# the number of snippets that contain warnings for that data set.
def sortUniqueSnippetsByDataset(datasets, uniqueSnippets):
    countSnippetsPerDataset = dict([(key.split("-")[1].strip(), 0) for key in datasets])
 
    for snippet in uniqueSnippets:
        #TODO: Could maybe simplify all these key.splits by doing it once when the list is first created if the other data is not needed
        snippet = snippet.split("-")[0].strip() # Name of snippets in 'uniqueSnippets' format example: COG Dataset 1 - 12
                                                #                                              format: Dataset Name - Snippet #
        if snippet in countSnippetsPerDataset:
            countSnippetsPerDataset[snippet] += 1

    return countSnippetsPerDataset

# Determines the number of warnings for each snippet, separated by the dataset the snippet is from.
# Creates a dictionary where the keys are the names of data sets. Values are a list where the size is 
# the TOTAL number of snippets in the dataset and values within the list are the number of warnings for a given snippet.
def getNumWarningsPerSnippetPerDataset(dfListAnalysisTools, correlationAnalysisDF):
    # Gets data from the dataframe corresponding to correlation_analysis.xlsx
    datasets = correlationAnalysisDF.iloc[:,0]  # A list of all datasets being used
    numSnippetsJudgedPerDataset = correlationAnalysisDF.iloc[:,1]   # A list of the number of snippets in each dataset

    # Setup the dictionary with its keys
    warningsPerSnippetPerDataset = dict([(key.split('-')[1].strip(), 0) for key in datasets])

    # Setup the dictionary with empty lists for its values
    # Size of the list corresponds to the total number of snippets in that dataset
    count = 0
    for dataset in warningsPerSnippetPerDataset:
        warningsPerSnippetPerDataset[dataset] = [0] * int(numSnippetsJudgedPerDataset[count])
        count += 1

    # Loop through the analysis tool output dataframes
    for df in dfListAnalysisTools:
        numWarnings = df.sum(axis=1, numeric_only=True).tolist()
        snippetNames = df['Snippet'].to_list()

        if len(snippetNames) != len(numWarnings):
            raise Exception("Number of snippets does not match number of warnings associated with said snippets") 

        for i in range(len(snippetNames)):
            snippetDataset = snippetNames[i].split('-')[0].strip()
            snippetNumber = snippetNames[i].split('-')[1].strip()

            #********************Temporary if statement
            #TODO change name of the fMRI snippets to match this standard i.e. "fMRI Dataset - 1"
            if snippetNumber.isnumeric():
                warningsPerSnippetPerDataset[snippetDataset][int(snippetNumber) - 1] += numWarnings[i]

    return warningsPerSnippetPerDataset

############################
#   Perform Correlations   #
############################

# OSCAR: probably with dataframes and slices, the code would be simpler
# OSCAR: we need to also compute Spearman Correlation, which doesn't assume normal distributions (scipy.stats.spearmanr)

# Perform Kendall's Tau correlation on each dataset seperatly where datapoints are: x = complexity metric, y = # of warnings
# Return a list of the correlation coefficients for each dataset.
def kendallTau(dfListCorrelationDatapoints):
    kendallTauVals = []

    #TODO TEMPORARY
    kendallTauVals.append('TEMP')

    # Loop through every sheet in datapoints.xlsx. A sheet corresponds to the datapoints for a specific dataset
    for df in dfListCorrelationDatapoints:
        x = df.iloc[:, 0]
        y = df.iloc[:, 1]

        corr, pValue = scpy.kendalltau(x, y)

        kendallTauVals.append(corr)

    #TODO TEMPORARY
    kendallTauVals.append('TEMP')

    return kendallTauVals

###########################
#   Program Starts Here   #
###########################

if __name__ == '__main__':
    # Read in all excel and csv sheets as dataframes
    dfListAnalysisTools = readAnalysisToolOutput()
    correlationAnalysisDF = readCorrelationAnalysis()

    # Update correlation analyis data frame 
    correlationAnalysisDF = setNumSnippetsWithWarningsColumn(dfListAnalysisTools, correlationAnalysisDF)

    # Compile all datapoints for correlation: x = complexity metric, y = # of warnings
    dfListCorrelationDatapoints = setupCorrelationData(dfListAnalysisTools, correlationAnalysisDF)

    # Update correlation analyis data frame 
    correlationAnalysisDF = setNumDatapointsForCorrelationColumn(dfListCorrelationDatapoints, correlationAnalysisDF)

    # Perform Correlations
    kendallTauVals = kendallTau(dfListCorrelationDatapoints)
    print(kendallTauVals)
    correlationAnalysisDF = setKendallTauColumn(kendallTauVals, correlationAnalysisDF)

    # Update correlation_analysis.xlsx
    writeCorrelationAnalysis(correlationAnalysisDF)
