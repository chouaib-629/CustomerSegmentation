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

- **Hadoop**: Distributed storage and processing framework.
- **Java**: Programming language for MapReduce logic.
- **Python**: For preprocessing the dataset.
- **Pandas, Matplotlib, Seaborn, SciPy**: Python libraries for preprocessing and statistical analysis.
- **HDFS**: Hadoop Distributed File System.

## Dataset Structure

The dataset used in this project is the [Online Retail](https://archive.ics.uci.edu/dataset/352/online+retail) dataset, which includes transactional data for a UK-based online retailer. The data contains the following fields:

| InvoiceNo | StockCode | Description                          | Quantity | InvoiceDate         | UnitPrice | CustomerID | Country          |
|-----------|-----------|--------------------------------------|----------|---------------------|-----------|------------|------------------|
| 536365    | 85123A    | WHITE HANGING HEART T-LIGHT HOLDER  | 6        | 2010-12-01 08:26:00 | 2.55      | 17850      | United Kingdom   |
| 536365    | 71053     | WHITE METAL LANTERN                | 6        | 2010-12-01 08:26:00 | 3.39      | 17850      | United Kingdom   |
| 536365    | 84406B    | CREAM CUPID HEARTS COAT HANGER     | 8        | 2010-12-01 08:26:00 | 2.75      | 17850      | United Kingdom   |

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

3. Preprocess the dataset using the provided Python script:

   - `.py` format:

     ```bash
     python preprocessing/main.py
     ```

   - `.ipynb` format:

     Open and run `preprocessing/main.ipynb` in Jupyter Notebook.

4. Compile the Java classes:

   ```bash
   javac -classpath `hadoop classpath` -d compiled_classes src/*.java
   ```

5. Package the classes into a JAR file:

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
   hdfs dfs -put input/processed_online_retail.csv /CustomerSegmentation/
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

