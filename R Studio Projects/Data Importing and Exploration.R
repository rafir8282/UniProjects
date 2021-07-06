# Introduction to Data Science (11372)
# Rafi Rahman, u3191010
# Assignment 1. Put all .csv files in working directory.

setwd("IDSAssignment1")
library(tidyverse)

################################################## PART A ##################################################

# Read data from files and store in tibble
m <- 8
y <- 2018
n <- 19
data <- tibble()

for (val in c(1:n)) {
    if (m > 12) {
        y <- y + 1
        m <- 1
    }
    file_id <- toString(y * 100 + m)
    data <-
        rbind(data, read_csv(paste(file_id, ".csv", sep = ""), skip = 7))
    m <- m + 1
}
rm(val, file_id, m, y, n)
print(data)

# Check for problems
if (nrow(problems(data)) == 0) {
    print("No problems found.")
} else {
    print("Please fix problems before you continue.")
}

################################################## PART B ##################################################

# Remove empty (NA >= 90%) columns from data
empty_col <- c()

for (val in c(1:ncol(data))) {
    col <- tibble(A = select(data, val))
    col_NA <- filter(col, is.na(A))
    if (nrow(col_NA) / nrow(col) >= 0.9) {
        empty_col <- append(empty_col, val)
    }
}

data <- select(data, -empty_col)
rm(val, col, col_NA, empty_col)
print(data)

# Rename variables
colnames(data) <- gsub(" ", "_", colnames(data))
print(data)

# Convert Date column from character type to date type
data$Date <- parse_date(data$Date, format = "%d/%m/%Y")
print(select(data, Date))

# Add Month and Year columns
data <- add_column(
    data,
    Month = as.character(data$Date, format = "%B"),
    Year = as.character(data$Date, format = "%Y")
)

data <- select(data, Date, Month, Year, everything())
print(data)

# Replace NA values in numeric columns with median
for (val in c(1:ncol(data))) {
    if (sapply(data[val], is.numeric)) {
        med <- median(data[[val]], na.rm = TRUE)
        data[val][is.na(data[val])] <- med
    }
}
rm(med, val)
print(select_if(data, is.numeric))

################################################## PART C ##################################################

#Print Summaries
summary(data$Minimum_temperature)
summary(data$`9am_Temperature`)
summary(data$`Speed_of_maximum_wind_gust_(km/h)`)

# Find minimum temperature average for each month (rounded to match data)
temp_tbl <- tibble()
minimum_temp_avg <- tibble(
    Month = character(),
    Year = character(),
    Average_minimum_temperature = double()
)

for (val in c(1:nrow(data))) {
    temp_tbl <- rbind(temp_tbl, data$Minimum_temperature[val])
    if (!identical(data$Month[val], data$Month[val + 1])) {
        minimum_temp_avg <- add_row(
            minimum_temp_avg,
            Month = data$Month[val],
            Year = data$Year[val],
            Average_minimum_temperature = round(mean(unlist(temp_tbl)), 1),
        )
        temp_tbl <- tibble()
    }
}
rm(val, temp_tbl)
print(minimum_temp_avg)

# Find maximum wind gust speed average for each maximum wind gust direction (rounded to match data)
temp_tbl <- select(drop_na(arrange(data, Direction_of_maximum_wind_gust)), 7, 8)
temp_tbl2 <- tibble()
maximum_wind_gust_speed_avg <- tibble(
    Maximum_wind_gust_direction = character(),
    Average_maximum_wind_gust_speed = double()
)

for (val in c(1:nrow(temp_tbl))) {
    temp_tbl2 <- rbind(temp_tbl2, temp_tbl$`Speed_of_maximum_wind_gust_(km/h)`[val])
    if (!identical(temp_tbl$Direction_of_maximum_wind_gust[val],
        temp_tbl$Direction_of_maximum_wind_gust[val + 1])) {
        maximum_wind_gust_speed_avg <- add_row(
            maximum_wind_gust_speed_avg,
            Maximum_wind_gust_direction = temp_tbl$Direction_of_maximum_wind_gust[val],
            Average_maximum_wind_gust_speed = round(mean(unlist(temp_tbl2)), 0),
        )
        temp_tbl2 <- tibble()
    }
}
rm(val, temp_tbl, temp_tbl2)
print(maximum_wind_gust_speed_avg)

# Find which month in which year had no rainfall
temp_tbl <- tibble()
check <- TRUE
check2 <- FALSE

for (val in c(1:nrow(data))) {
    temp_tbl <- rbind(temp_tbl, data$`Rainfall_(mm)`[val])
    if (data$`Rainfall_(mm)`[val] != 0) {
        check <- FALSE
    }
    if (!identical(data$Month[val], data$Month[val + 1])) {
        temp_tbl <- tibble()
        if (check == TRUE) {
            print(paste(data$Month[val], ", ", data$Year[val], " was a dry month.", sep = ""))
            check2 <- TRUE
        }
    }
}

if (check2 == FALSE) {
    print("There were no dry months.")
}
rm(val, temp_tbl, check, check2)

# Find month in 2019 with the highest humidity level using 3pm_relative_humidity(%) monthly averages
temp_tbl <- select(filter(data, Year == "2019"), 2, 17)
temp_tbl2 <- tibble()
humidity_avg <- tibble(
    Month = character(),
    Average_humidity_3pm = double()
)

for (val in c(1:nrow(temp_tbl))) {
    temp_tbl2 <- rbind(temp_tbl2, temp_tbl$`3pm_relative_humidity_(%)`[val])
    if (!identical(temp_tbl$Month[val], temp_tbl$Month[val + 1])) {
        humidity_avg <- add_row(
            humidity_avg,
            Month = temp_tbl$Month[val],
            Average_humidity_3pm = mean(unlist(temp_tbl2)), # Not rounded for the sake of comparison
        )
        temp_tbl2 <- tibble()
    }
}
print(humidity_avg)

max <- which.max(as.vector(humidity_avg$Average_humidity_3pm))

print(paste("The month with the highest humidity level in 2019 is", humidity_avg$Month[max]))
rm(temp_tbl, temp_tbl2, max, val)