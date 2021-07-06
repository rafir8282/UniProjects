# Introduction to Data Science (11372)
# Rafi Rahman, u3191010
# Final Assessment. Put all .csv files in working directory. Install corrplot.

setwd("IDSFinal")

### REQUIRED ####
# install.packages("corrplot")

library(tidyverse)
library(lubridate)
library(corrplot)

################################################## TASK 1 ##################################################

# Question 1
countries_csv <- read_csv("Countries.csv")
covid19_csv <- read_csv("Covid19.csv")
recovered_csv <- read_csv("Recovered.csv")
tests_csv <- read_csv("Tests.csv")

# Question 2
recovered_csv <- as_tibble(
    gather(recovered_csv, date, new_recovered, `2020.01.22`:`2020.05.05`, factor_key = TRUE)
)
recovered_csv$date <- parse_date(as.character(recovered_csv$date), format = "%Y.%m.%d")
colnames(recovered_csv)[1] <- "location"
recovered_csv <- arrange(recovered_csv, location)

# Question 3
colnames(countries_csv) <- c("Code", "Country", "Population", "GDP", "GDPCapita")
colnames(covid19_csv) <- c("Code", "Country", "Continent", "Date", "NewCases", "NewDeaths")
colnames(recovered_csv) <- c("Country", "Date", "Recovered")
colnames(tests_csv) <- c("Code", "Date", "NewTests")

# Question 4
# All date columns are in the date format.

# Question 5
covid19_csv <- merge(covid19_csv, countries_csv)
covid19_csv <- merge(covid19_csv, recovered_csv)
covid19_csv <- merge(covid19_csv, tests_csv)
covid19_csv <- covid19_csv[, c(1, 2, 3, 4, 7, 8, 9, 5, 6, 10, 11)]

# Question 6
for (val in c(1:ncol(covid19_csv))) {
    if (sapply(covid19_csv[val], is.numeric)) {
        covid19_csv[val][is.na(covid19_csv[val])] <- 0
    }
}
rm(val)

for (val in c(1:ncol(countries_csv))) {
    if (sapply(countries_csv[val], is.numeric)) {
        countries_csv[val][is.na(countries_csv[val])] <- 0
    }
}
rm(val)

for (val in c(1:ncol(recovered_csv))) {
    if (sapply(recovered_csv[val], is.numeric)) {
        recovered_csv[val][is.na(recovered_csv[val])] <- 0
    }
}
rm(val)

for (val in c(1:ncol(tests_csv))) {
    if (sapply(tests_csv[val], is.numeric)) {
        tests_csv[val][is.na(tests_csv[val])] <- 0
    }
}
rm(val)

# Question 7
covid19_csv <- add_column(
    covid19_csv,
    Month = lubridate::month(ymd(covid19_csv$Date)),
    Week = lubridate::isoweek(ymd(covid19_csv$Date))
)
covid19_csv <- select(covid19_csv, Date, Month, Week, everything())

################################################## TASK 2 ##################################################

# Questions 1, 2, and 3
covid19_csv <- arrange(covid19_csv, Country, Date)
tbl_y <- tibble()
tbl_z <- tibble()

for (val in c(1:nrow(covid19_csv))) {
    tbl_z <- rbind(tbl_z, slice(covid19_csv, val))
    if (!identical(covid19_csv$Country[val], covid19_csv$Country[val + 1])) {
        tbl_z <- mutate(
            tbl_z,
            CumCases = cumsum(tbl_z$NewCases),
            CumDeaths = cumsum(tbl_z$NewDeaths),
            CumRecovered = cumsum(tbl_z$Recovered),
            CumTests = cumsum(tbl_z$NewTests),
            Active = CumCases - CumDeaths + CumRecovered,
            FatalityRate = round(CumDeaths/CumCases, 4),
            Cases_1M_Pop = round(CumCases*(10^6)/Population, 4),
            Deaths_1M_Pop = round(CumDeaths*(10^6)/Population, 4),
            Recovered_1M_Pop = round(CumRecovered*(10^6)/Population, 4),
            Tests_1M_Pop = round(CumTests*(10^6)/Population, 4)
        )
        tbl_y <- rbind(tbl_y, tbl_z)
        tbl_z <- tibble()
    }
}

covid19_csv <- tbl_y
rm(tbl_y, tbl_z, val)

for (val in c(1:ncol(covid19_csv))) {
    if (sapply(covid19_csv[val], is.numeric)) {
        covid19_csv[val][is.na(covid19_csv[val])] <- 0 # Resolve 0/0 values for FatalityRate
    }
}
rm(val)

# Question 4
covid19_csv <- arrange(covid19_csv, Date)
tbl_x <- tibble()
tbl_y <- tibble(
    Date = character(),
    TotalDeaths = double()
)

for (val in c(1:nrow(covid19_csv))) {
    tbl_x <- rbind(tbl_x, covid19_csv$NewDeaths[val])
    if (!identical(covid19_csv$Date[val], covid19_csv$Date[val + 1])) {
        tbl_y <- add_row(
            tbl_y,
            Date = as.character.Date(covid19_csv$Date[val]),
            TotalDeaths = sum(tbl_x),
        )
        tbl_x <- tibble()
    }
}

highest_death <- slice(tbl_y, which.max(tbl_y$TotalDeaths))
highest_death$Date <- parse_date(highest_death$Date)
rm(val, tbl_x, tbl_y)
covid19_csv <- arrange(covid19_csv, Country, Date)
print(highest_death)

# Question 5
covid19_csv <- arrange(covid19_csv, Date)
tbl_x <- tibble()
tbl_y <- tibble(
    Date = character(),
    Cases = double(),
    Deaths = double(),
    Recovered = double(),
    Tests = double()
)

for (val in c(1:nrow(covid19_csv))) {
    tbl_x <- rbind(tbl_x, slice(select(covid19_csv, NewCases, NewDeaths, Recovered, NewTests), val))
    if (!identical(covid19_csv$Date[val], covid19_csv$Date[val + 1])) {
        tbl_y <- add_row(
            tbl_y,
            Date = as.character.Date(covid19_csv$Date[val]),
            Cases = sum(tbl_x$NewCases),
            Deaths = sum(tbl_x$NewDeaths),
            Recovered = sum(tbl_x$Recovered),
            Tests = sum(tbl_x$NewTests)
        )
        tbl_x <- tibble()
    }
}

tbl_y <- mutate(
    tbl_y,
    CumCases = cumsum(tbl_y$Cases),
    CumDeaths = cumsum(tbl_y$Deaths),
    CumRecovered = cumsum(tbl_y$Recovered),
    CumTests = cumsum(tbl_y$Tests)
)
tbl_y <- select(tbl_y, 1, c(6:9))
tbl_y$Date <- parse_date(tbl_y$Date)

ggplot(tbl_y, aes(x = Date, group = 1)) +
    geom_line(aes(y = CumCases, group = 1, color = "cases")) +
    geom_line(aes(y = CumDeaths, group = 1, color = "deaths")) +
    geom_line(aes(y = CumRecovered, group = 1, color = "recovered")) +
    geom_line(aes(y = CumTests, group = 1, color = "tests")) +
    scale_y_continuous(trans = 'log10') +
    theme_classic() +
    ylab(NULL)

covid19_csv <- arrange(covid19_csv, Country, Date)
rm(tbl_x, tbl_y, val)

# Question 6
lastDay_data <- filter(covid19_csv, Date == as.Date("2020-05-05"))

# Question 7
top10activeW <- head(arrange(lastDay_data, desc(Active)), n = 10)
top10casesW <- head(arrange(lastDay_data, desc(CumCases)), n = 10)
top10fatalityW <- head(arrange(lastDay_data, desc(FatalityRate)), n = 10)

# Question 8
lastDay_data <- arrange(lastDay_data, Continent)
tbl_x <- tibble()
tbl_y <- tibble(
    Continent = character(),
    TotalCases = double(),
    TotalDeaths = double(),
    TotalRecovered = double(),
    TotalTests = double()
)

for (val in c(1:nrow(lastDay_data))) {
    tbl_x <- rbind(slice(select(lastDay_data, CumCases, CumDeaths, CumRecovered, CumTests), val))
    if (!identical(lastDay_data$Continent[val], lastDay_data$Continent[val + 1])) {
        tbl_y <- add_row(
            tbl_y,
            Continent = lastDay_data$Continent[val],
            TotalCases = sum(tbl_x$CumCases),
            TotalDeaths = sum(tbl_x$CumDeaths),
            TotalRecovered = sum(tbl_x$CumRecovered),
            TotalTests = sum(tbl_x$CumTests)
        )
        tbl_x <- tibble()
    }
}

continent_totals <- tbl_y
rm(tbl_x, tbl_y, val)
print(continent_totals)

# Question 9
tbl_x <- tibble()

for (val in c(1:nrow(top10casesW))) {
    tbl_x <- rbind(tbl_x, filter(covid19_csv, Country == top10casesW$Country[val]))
}

tbl_x <- select(tbl_x, Date, Country, CumCases)
tbl_x <- spread(tbl_x, Country, CumCases)
tbl_x <- fill(tbl_x, everything(), .direction = "down")

for (val in c(1:ncol(tbl_x))) {
    if (sapply(tbl_x[val], is.numeric)) {
        tbl_x[val][is.na(tbl_x[val])] <- 0
    }
}

ggplot(tbl_x, aes(x = Date, group = 1)) +
    geom_line(aes(y = Canada, group = 1, color = "canada")) +
    geom_line(aes(y = France, group = 1, color = "france")) +
    geom_line(aes(y = Germany, group = 1, color = "germany")) +
    geom_line(aes(y = Iran, group = 1, color = "iran")) +
    geom_line(aes(y = Italy, group = 1, color = "italy")) +
    geom_line(aes(y = Russia, group = 1, color = "russia")) +
    geom_line(aes(y = Spain, group = 1, color = "spain")) +
    geom_line(aes(y = Turkey, group = 1, color = "turkey")) +
    geom_line(aes(y = `United Kingdom`, group = 1, color = "uk")) +
    geom_line(aes(y = `United States of America`, group = 1, color = "usa")) +
    scale_y_continuous(trans = 'log10') +
    theme_classic() +
    ylab(NULL)
rm(val, tbl_x)

# Question 10
tbl_x <- tibble()

for (val in c(1:nrow(top10activeW))) {
    tbl_x <- rbind(tbl_x, filter(covid19_csv, Country == top10activeW$Country[val]))
}

tbl_x <- select(tbl_x, Date, Country, NewCases, NewDeaths, Recovered)

ggplot(tbl_x, aes(x = Date, group = 1)) +
    facet_grid(rows = vars(Country)) +
    geom_line(aes(y = NewCases, group = 1, color = "cases")) +
    geom_line(aes(y = NewDeaths, group = 1, color = "deaths")) +
    geom_line(aes(y = Recovered, group = 1, color = "recovered")) +
    scale_y_continuous(trans = 'log10', breaks = c(1, 100, 10000)) +
    ylab(NULL)
rm(tbl_x, val)

################################################## TASK 3 ##################################################

# rmse <- mean((actual - predicted)^2)

# Question 1
cor_data <- select(lastDay_data, CumCases, CumTests, Population, GDP, GDPCapita)

# Question 2
cor_matrix <- round(cor(cor_data), 4)
corrplot(cor_matrix, type = "upper", order = "hclust", tl.col = "black", tl.srt = 45)

# Question 3
sample <- sample(c(TRUE, FALSE), nrow(cor_data), replace = T, prob = c(0.65, 0.35))
train <- cor_data[sample,]
test <- cor_data[!sample,]
rm(sample)

# Question 4
model <- lm(CumCases ~ GDP, data = train)
prediction <- predict(model, test)
actual <- test$CumCases
rmse <- round(mean((actual - prediction)^2), 4)
print(rmse)

# Question 5
multi_model <- lm(CumCases ~ ., data = train)
prediction2 <- predict(multi_model, test)
rmse2 <- round(mean((actual - prediction2)^2), 4)
print(rmse2)