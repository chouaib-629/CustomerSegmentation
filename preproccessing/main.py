# %%
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from scipy.stats import ttest_ind

# %% [markdown]
# # Step 1: Load the Dataset

# %%
data = pd.read_csv("../input/online_retail.csv")
data.head()

# %% [markdown]
# # Step 2: Clean the Data

# %%
data.isna().sum()

# %% [markdown]
# ### Drop rows with missing values

# %%
data.dropna(subset=["Description", "CustomerID"], inplace=True)

# %% [markdown]
# ### Convert InvoiceDate to datetime

# %%
data['InvoiceDate'] = pd.to_datetime(data['InvoiceDate'])

# %% [markdown]
# ### Convert CustomerID from float to int

# %%
data['CustomerID'] = data['CustomerID'].astype(int)

# %% [markdown]
# # Step 3: Add a Total Sales Column

# %%
data['TotalSales'] = data['Quantity'] * data['UnitPrice']

# %% [markdown]
# # Step 4: Save the processed dataset to a CSV file

# %%
data.head()

# %%
data.to_csv('../input/processed_online_retail.csv', index=False) 

# %% [markdown]
# # Step 5: Visualizations

# %% [markdown]
# ### a) Sales Distribution per Country

# %%
country_sales = data.groupby('Country')['TotalSales'].sum().sort_values(ascending=False)
plt.figure(figsize=(10, 6))
sns.barplot(x=country_sales.values, y=country_sales.index, palette="viridis")
plt.title("Total Sales by Country")
plt.xlabel("Total Sales")
plt.ylabel("Country")
plt.show()

# %% [markdown]
# ### b) Most Sold Products

# %%
product_sales = data.groupby('Description')['Quantity'].sum().sort_values(ascending=False).head(10)
plt.figure(figsize=(10, 6))
sns.barplot(x=product_sales.values, y=product_sales.index, palette="magma")
plt.title("Top 10 Most Sold Products")
plt.xlabel("Quantity Sold")
plt.ylabel("Product Description")
plt.show()

# %% [markdown]
# ### c) Revenue Over Time

# %%
daily_revenue = data.groupby(data['InvoiceDate'].dt.date)['TotalSales'].sum()
plt.figure(figsize=(12, 6))
plt.plot(daily_revenue.index, daily_revenue.values, marker='o', color='teal')
plt.title("Daily Revenue")
plt.xlabel("Date")
plt.ylabel("Total Revenue")
plt.xticks(rotation=45)
plt.show()

# %% [markdown]
# # Step 6: Statistical Tests

# %% [markdown]
# ### a) Correlation between Quantity and UnitPrice

# %%
correlation = data['Quantity'].corr(data['UnitPrice'])
print(f"Correlation between Quantity and UnitPrice: {correlation:.2f}")

# %% [markdown]
# ### b) t-test: Average sales difference between UK and Germany

# %%
uk_sales = data[data['Country'] == "United Kingdom"]['TotalSales']
germany_sales = data[data['Country'] == "Germany"]['TotalSales']

t_stat, p_value = ttest_ind(uk_sales, germany_sales, equal_var=False)
print(f"T-test between UK and Germany sales:")
print(f"T-statistic: {t_stat:.2f}, P-value: {p_value:.4f}")

# %% [markdown]
# # Step 6: Interpretation of Results
# 
# #### **1. Correlation between `Quantity` and `UnitPrice`: -0.00**
# - A correlation coefficient of **-0.00** indicates **no linear relationship** between the quantity of products sold and their unit price.
# - This suggests that the quantity of products sold is not directly influenced by their price in this dataset.
# 
# #### **2. T-Test between UK and Germany Sales**
# - **T-Statistic**: -5.51 indicates a significant difference between the average sales in the UK and Germany.
# - **P-value**: 0.0000 (very small) suggests that the difference is statistically significant at any common significance level (e.g., 0.05).
#   
#    **Conclusion**:
#    - The average sales in the UK and Germany are significantly different.
#    - This could be due to differences in purchasing behavior, the number of customers, or other country-specific factors.


