# Introduction to Data Science (11372)
# Rafi Rahman, u3191010
# Assignment 2. Put all .csv files in working directory.

setwd("IDSAssignment2")
library(tidyverse)

################################################## PART A ##################################################

# Read data from files and store in tibbles
group1 <- read_csv("Group1.csv")
group2 <- read_csv("Group2.csv")
group3 <- read_csv("Group3.csv")
group4 <- read_csv("Group4.csv")
group5 <- read_csv("Group5.csv")
group6 <- read_csv("Group6.csv")
group7 <- read_csv("Group7.csv")
group8 <- read_csv("Group8.csv")
group9 <- read_csv("Group9.csv")
group10 <- read_csv("Group10.csv")

group_files <- bind_rows(group1, group2, group3, group4, group5, group6, group7, group8, group9, group10)
print(group_files)

participants_file <- read_csv("participants.csv")
print(participants_file)

test_participant_file <- read_csv("test_participants.csv")
print(test_participant_file)

# Extract total calories of each group
G1 <- sum(group1$calories)
print(paste("Group 1:", G1))

G2 <- sum(group2$calories)
print(paste("Group 2:", G2))

G3 <- sum(group3$calories)
print(paste("Group 3:", G3))

G4 <- sum(group4$calories)
print(paste("Group 4:", G4))

G5 <- sum(group5$calories)
print(paste("Group 5:", G5))

G6 <- sum(group6$calories)
print(paste("Group 6:", G6))

G7 <- sum(group7$calories)
print(paste("Group 7:", G7))

G8 <- sum(group8$calories)
print(paste("Group 8:", G8))

G9 <- sum(group9$calories)
print(paste("Group 9:", G9))

G10 <- sum(group10$calories)
print(paste("Group 10:", G10))


# Total calories for each participant's chosen group
group <- data.frame(
    Total_calories = c(G1, G2, G3, G4, G5, G6, G7, G8, G9, G10),
    Title = c("G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10")
)

x <- arrange(merge(participants_file, group, by.x = "Groups", by.y = "Title"), SubjectId)
x <- select(x, 2, 3, everything())
print(x)

# Questions 4 and 5
participants_file <- mutate(x, Comsumed_Calories = x$Total_calories + x$ExtraCalories)
print(participants_file)

################################################## PART B ##################################################

# Extract frequency table of diet groups by gender
gender_subject_feq <- table(participants_file$Gender, participants_file$Groups)
print(gender_subject_feq)

# Extract proportion table of diet groups per gender
proportions <- prop.table(gender_subject_feq)
print(proportions)

# Compute probability for females of group 5 being chosen
prob5f <- (proportions["F", "G5"] / margin.table(proportions, 2)["G5"]) * 100
print(prob5f)

# Compute probability for males of group 5 being chosen
prob5m <- (proportions["M", "G5"] / margin.table(proportions, 1)["M"]) * 100
print(prob5m)

# Visualisation chart to show frequencies of selected diet groups per gender
ggplot(participants_file, aes(x = participants_file$Groups, fill = participants_file$Gender)) +
    geom_bar(position = "dodge")

# Quantiles of gained calories
Consumed_Calories_quartiles <- quantile(participants_file$Comsumed_Calories)
print(Consumed_Calories_quartiles)

# Quantiles of gained weights
GW_quartiles <- quantile(participants_file$GW)
print(GW_quartiles)

# Average weight gained per gender
maleDf <- subset(participants_file, Gender == "M")
femaleDf <- subset(participants_file, Gender == "F")

print(paste("Male:", round(mean(maleDf$GW), 6)))
print(paste("Female:", round(mean(femaleDf$GW), 6)))

# Graph of density distribution of gained weights for each gender
ggplot() + geom_density(
    data= participants_file,
    aes(x = GW, group = participants_file$Gender, color = Gender), adjust = 2
) + xlab("Gender") + ylab("Density") + theme_classic()

################################################## PART C ##################################################

# Correlation between gained weights and consumed calories
correlation <- cor(participants_file$GW, participants_file$Comsumed_Calories)
print(correlation)

# Graph of relationship between consumed calories and gained weights
ggplot(participants_file, aes(x = participants_file$Comsumed_Calories, y = participants_file$GW)) +
    geom_point() + stat_smooth(method = lm)

# Train linear regression model to describe gained weight from consumed calories
lmModel1 <- lm(GW~Comsumed_Calories, data = participants_file)
print(lmModel1)
summary(lmModel1)

# Coefficient values
print(lmModel1$coefficients)

# Question 5
# The R-squared and adjacent R-squared values tell us that the explanatory variable intercept can
# explain ~48% of the response variable variance.

# Residuals highlighted
ggplot(lmModel1, aes(lmModel1$residuals)) + geom_histogram(aes(y = ..density..), fill = "#C99800") +
    geom_density(color = "red")