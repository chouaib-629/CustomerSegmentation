# Customer Segmentation Using Hadoop

This project uses Hadoop MapReduce to perform customer segmentation based on the dataset from an [Online Retail store](https://archive.ics.uci.edu/dataset/352/online+retail). The objective is to calculate three key metrics for each customer:

- Total Spend
- Frequency (number of purchases)
- Recency (time in days since the last purchase)

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Dataset Structure](#dataset-structure)
- [Usage](#usage)
- [Contributing](#contributing)
- [Contact Information](#contact-information)

## Features

- Hadoop-based scalable processing for large datasets.
- Key customer segmentation metrics: TotalSpend, Frequency, and Recency.
- Preprocessing script for dataset preparation.
- MapReduce implementation for distributed data processing.

## Technologies Used

- **Hadoop**: A framework for distributed storage and processing of big data.
- **Java**: The primary programming language used for writing the MapReduce logic.
- **HDFS**: The Hadoop Distributed File System for storing large datasets.
- **MapReduce**: The processing model used to process data.
- **Python**: For dataset preprocessing and analysis.
- **Libraries**: pandas, matplotlib, seaborn, scipy.stats
- **Linux (Ubuntu)**: Operating System.

## Dataset Structure

The dataset used in this project is the [Online Retail Dataset](https://archive.ics.uci.edu/dataset/352/online+retail). It contains all transactions occurring between 01/12/2010 and 09/12/2011 for a UK-based and registered non-store online retailer. The company mainly sells unique all-occasion gifts, with many of its customers being wholesalers.

**Sample of the dataset:**

| InvoiceNo | StockCode | Description                        | Quantity | InvoiceDate         | UnitPrice (Sterling) | CustomerID | Country        |
| --------- | --------- | ---------------------------------- | -------- | ------------------- | --------- | ---------- | -------------- |
| 536365    | 85123A    | WHITE HANGING HEART T-LIGHT HOLDER | 6        | 01/12/2010 8:26 | 2.55      | 17850.0     | United Kingdom |
| 536365    | 71053     | WHITE METAL LANTERN                | 6        | 01/12/2010 8:26 | 3.39      | 17850.0     | United Kingdom |
| 536365    | 84406B    | CREAM CUPID HEARTS COAT HANGER     | 8        | 01/12/2010 8:26 | 2.75      | 17850.0     | United Kingdom |
| 536365    | 84029G 	| KNITTED UNION FLAG HOT WATER BOTTLE | 6	      | 12/1/2010 8:26	 | 3.39      | 17850.0    | United Kingdom |
| 536365    |	84029E   |	RED WOOLLY HOTTIE WHITE HEART.   | 6         | 12/1/2010 8:26   | 3.39      | 17850.0     | United Kingdom |

## Getting Started

To get started with this project, follow these steps:

### Prerequisites

1. Install Hadoop on your local machine or use a cloud-based Hadoop cluster.
2. Ensure that Java (JDK 8 or later) is installed on your system.
3. Install Python and required libraries:

   ```bash
   pip install pandas matplotlib seaborn scipy
   ```

4. Download the [Online Retail Dataset](https://archive.ics.uci.edu/ml/datasets/Online+Retail).

### Setup Instructions

1. Clone this repository:

   ```bash
   git clone https://github.com/chouaib-629/CustomerSegmentation.git
   ```

2. Navigate to the project directory:

   ```bash
   cd CustomerSegmentation
   ```

3. The downloaded dataset is in `Online Retail.xlsx` format. Save it as `online_retail.csv` using any spreadsheet tool.

4. Preprocess the dataset using the provided Python script:

   - `.py` format:

     ```bash
     python preprocessing/main.py
     ```

   - `.ipynb` format:

     Open and run `preprocessing/main.ipynb` in Jupyter Notebook.

5. Compile the Java classes:

   ```bash
   javac -classpath `hadoop classpath` -d compiled_classes src/*.java
   ```

6. Package the classes into a JAR file:

   ```bash
   jar cf CustomerSegmentation.jar -C compiled_classes/ .
   ```

## Usage

### Step 1: Upload Dataset to HDFS

1. Create a directory in HDFS to store the dataset:

   ```bash
   hdfs dfs -mkdir /CustomerSegmentation
   ```

2. Upload the preprocessed dataset to HDFS:

   ```bash
   hdfs dfs -put processed_online_retail.csv /CustomerSegmentation/
   ```

### Step 2: Run the MapReduce Job

Run the Hadoop job using the following command:

```bash
hadoop jar CustomerSegmentation.jar Driver /CustomerSegmentation/processed_online_retail.csv /CustomerSegmentation/output/
```

### Step 3: View the Output

To view the results of the MapReduce job, use the following command:

```bash
hdfs dfs -cat /CustomerSegmentation/output/part-r-00000
```

### **Optional:** Save the Output Locally

Copy the output file from HDFS to your local storage for further analysis:

```bash
hdfs dfs -get /CustomerSegmentation/output/part-r-00000 output/result.csv
```

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch:

   ```bash
   git checkout -b feature/feature-name
   ```

3. Commit your changes:

   ```bash
   git commit -m "Add feature description"
   ```

4. Push to the branch:

   ```bash
   git push origin feature/feature-name
   ```

5. Open a pull request.

## Contact Information

For questions or support, please contact [Me](mailto:your-email@example.com).

