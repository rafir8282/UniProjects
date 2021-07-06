setwd("R Studio")

library(tidyverse)
library(ggplot2)

################################################################################

# Question 1

# A
?ChickWeight

# The ChickWeight data frame has 578 rows and 4 columns from an experiment on
# the effect of diet on early growth of chicks.

# B
ggplot(ChickWeight, aes(x = weight, y = Diet, color = Diet)) + geom_boxplot()

# The boxplot shows us that the median weights all fall in the 100 g range, with
# diet 3 giving us the heaviest weights.

# C
ggplot(ChickWeight, aes(x = weight, y = Diet, color = Diet)) +
         geom_boxplot() +
         xlab("Chick Weight (g)") +
         ylab("Diet Type") +
         ggtitle("Chick Weight vs Diet Type") +
         theme_classic()

# Labels and axis title help to improve the interpretability of the graph, as
# well as the theme which, overall allowing for better presentation.

# D
ggplot(ChickWeight, aes(x = weight, y = Diet, color = Diet)) +
         geom_boxplot() +
         xlab("Chick Weight (g)") +
         ylab("Diet Type") +
         ggtitle("Chick Weight vs Diet Type") +
         theme_classic() +
         geom_jitter()

# The jitter function allows us to compare the distribution of weight for each
# type of diet in one parallel plot. In a boxplot, information may be lost, such
# as accurate distribution and number of observations (lower numbers may allow
# for higher medians etc.). Using geom_jitter() will allow us to see all the
# points and their rough distributions.

# E
cw_median <- ChickWeight %>%
         group_by(Time) %>% summarise(m_weight = median(weight))

ggplot(cw_median, aes(x = Time, y = m_weight, color = m_weight)) +
         geom_point() +
         geom_smooth(method = lm) +
         ylab("Median Weight (g)") +
         xlab("Time") +
         ggtitle("Median Weight vs Time") +
         theme_classic()

# The graph doesn't take into account the diet types and the effects it would
# have on the chicks' weights.

################################################################################

# Question 2

# A
?ToothGrowth

# The response is the length of odontoblasts (cells responsible for tooth
# growth) in 60 guinea pigs. Each animal received one of three dose levels of
# vitamin C (0.5, 1, and 2 mg/day) by one of two delivery methods, orange juice
# or ascorbic acid (a form of vitamin C and coded as VC).

# B
ggplot(ToothGrowth, aes(x = len)) +
         geom_histogram()

# C
# There is no clear trend, but the histogram seems to slightly resemble a
# positively skewed bell curve. The mode appears to be between 25 and 30.

# D
ggplot(ToothGrowth, aes(x = len, fill = ..count..)) +
         geom_histogram(bins = 8) +
         xlab("Teeth Length (mm)") +
         ylab("Count") +
         ggtitle("Occurrence of Teeth Lengths") +
         theme_classic()

# E
ggplot(ToothGrowth, aes(x = len, fill = ..count..)) +
         geom_histogram(bins = 8, binwidth = 6) +
         xlab("Teeth Length (mm)") +
         ylab("Count") +
         ggtitle("Occurrence of Teeth Lengths") +
         theme_classic()

# In this case, bins = 8 is pretty suitable for this data set, but for binwidth,
# a value of 6 is very suitable as it helps to show the data as a normalised
# distribution.

# There are other suitable binwidths, as long as they are not too wide or too
# granular; a suitable binwidth must be chosen so that certain values are not
# over-represented, and you get the full picture of what the data is trying to
# tell you

# F
ggplot(ToothGrowth, aes(x = dose, fill = ..count..)) +
        geom_bar() +
        xlab("Dose (mg/day)") +
        ylab("Count") +
        ggtitle("Occurrence of Teeth Lengths") +
        theme_classic()

# G
tg_median <- ToothGrowth %>%
        group_by(dose) %>% summarise(m_length = median(len))

# H
tg_median2 <- ToothGrowth %>%
        group_by(dose, supp) %>% summarise(m_length = median(len))

# I
tg_mean <- ToothGrowth %>%
        group_by(dose) %>% summarise(avg_length = mean(len))

# K
ggplot(ToothGrowth, aes(x = dose, y = len)) +
        geom_point() +
        xlab("Dose (mg/day)") +
        ylab("Tooth Length (mm)") +
        ggtitle("Tooth Length vs Dose") +
        theme_classic()

# L
# We can add jitter to the scatter plot to remedy overplotting, thus improving
# the interpretability of the plot. We can also add a trend line, which helps to
# show the affect higher doses have on tooth growth.

ggplot(ToothGrowth, aes(x = dose, y = len)) +
        geom_point() +
        geom_jitter() +
        geom_smooth() +
        xlab("Dose (mg/day)") +
        ylab("Tooth Length (mm)") +
        ggtitle("Tooth Length vs Dose") +
        theme_classic()

################################################################################

# Question 3

# A
?storms

# This data is a subset of the NOAA Atlantic hurricane database best track data,
# https://www.nhc.noaa.gov/data/#hurdat. The data includes the positions and
# attributes of 198 tropical storms, measured every six hours during the
# lifetime of a storm.

# B
ggplot(storms, aes(x = pressure, y = wind)) +
        geom_point() +
        geom_jitter() +
        xlab("Pressure (mBar)") +
        ylab("Wind (Knots)") +
        ggtitle("Wind vs Pressure") +
        theme_classic()

# We can add jitter to the scatter plot to remedy overplotting, thus improving
# the interpretability of the plot.

# C
ggplot(storms, aes(x = pressure, y = wind)) +
        geom_point() +
        geom_jitter() +
        geom_smooth(method = lm) +
        xlab("Pressure (mBar)") +
        ylab("Wind (Knots)") +
        ggtitle("Wind vs Pressure") +
        theme_classic()

# D
ggplot(storms, aes(x = long, y = lat)) +
        geom_bin2d()

# The higher counts on the density map tells us that there were more storms at
# the specific coordinates, which implies that the wind speeds in those
# coordinates are higher.

# E
ggplot(storms, aes(x = category, y = wind, color = category)) +
        geom_boxplot() +
        xlab("Category") +
        ylab("Wind (Knots)") +
        ggtitle("Wind Speed vs Category") +
        theme_classic()

# G
ggplot(storms, aes(x = month, y = pressure, color = month)) +
        geom_point() +
        geom_jitter() +
        xlab("Month") +
        ylab("Pressure (mBar)") +
        ggtitle("Variation in Pressure by Month") +
        theme_classic()

ggplot(storms, aes(x = status, y = pressure, color = status)) +
        geom_boxplot() +
        xlab("Status") +
        ylab("Pressure (mBar)") +
        ggtitle("Variation in Air Pressure by Status") +
        theme_classic()