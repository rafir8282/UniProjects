library(tidyverse)
library(ggplot2)
library(dplyr)

################################################################################

# Question A

# Part 1
ggplot(quakes, aes(x = stations, y = mag)) +
  geom_point() +
  ggtitle("Earthquake Number of Stations vs. Magnitude") +
  xlab("Stations") +
  ylab("Magnitude") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

# Part 2
ggplot(quakes, aes(x = stations, y = mag)) +
  geom_point(colour = "orange") +
  geom_jitter(colour = "orange") +
  geom_smooth(method = "lm", colour = "Black", se = F) +
  ggtitle("Earthquake Number of Stations vs. Magnitude") +
  xlab("Stations") +
  ylab("Magnitude") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

# For improving the graph's readability, we can use geom_jitter() to correct
# overplotting by adding random noise to the results.Adding a trendline helps us
# understand the relationship between the variables easily. To make the points
# more visible, they can be coloured.

# Part 3
cor_mag_stations <- cor(quakes$mag, quakes$stations)

# From the trendline, we can see that there is a linear relationship between
# the magnitude and the stations. Stations don't tend to report lower magnitude
# earthquakes, as the stations would need to be located closer to the epicentre
# of the earthquake. By using the cor function, We get a coefficient value of
# 0.8511824, meaning since it is closer to 1, there is a strong correlation
# between the magnitude and the stations.

# Part 4
# We should monitor the pattern between variable values so that the data can be
# understood without the logic becoming biased.

# Part 5
ggplot(quakes, aes(x = stations, y = mag, color = depth)) +
  geom_point() +
  geom_jitter() +
  geom_smooth(method = "lm", colour = "Black", se = F) +
  scale_color_gradient(low = "skyblue", high ="navyblue") +
  ggtitle("Earthquake Number of Stations vs. Magnitude") +
  xlab("Stations") +
  ylab("Magnitude") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

ggplot(quakes, aes(x = depth, y = mag)) +
  geom_point() +
  geom_jitter() +
  ggtitle("Earthquake Depth vs. Magnitude") +
  xlab("Depth") +
  ylab("Magnitude") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

cor_depth_mag <- cor(quakes$depth, quakes$mag)

# Depth does not matter here. We can see in the above graph that the pattern is
# quite vague. With the cor function, We get a value of -0.2306377, meaning
# the correlation is weak, as it is closer to 0.

# Part 6
ggplot(quakes, aes(x = lat, y = long)) +
  geom_point() +
  ggtitle("Earthquake Latitude vs. Longitude") +
  xlab("Latitude") +
  ylab("Longitude")

# Part 7
ggplot(quakes, aes(x = lat, y = long, colour = stations)) +
  geom_point() +
  ggtitle("Earthquake Latitude vs. Longitude") +
  scale_color_gradient(low = "lightpink", high ="navy") +
  xlab("Latitude") +
  ylab("Longitude") +
  theme(plot.title = element_text(size = 14, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 12, hjust = 0.5),
        axis.title.y = element_text(size = 12, hjust = 0.5))

# By adding colour, we can see a rise in stations reporting earthquakes at
# certain latitudes and longitudes.

################################################################################

# Question B

# Part 1
length_manufacturer <- length(unique(mpg$manufacturer))

# Number of unique manufacturers: 15

# Part 2
ggplot(mpg, aes(x = hwy, fill = ..count..)) +
  geom_histogram(binwidth = 2) +
  ggtitle("Highway MPG vs. Number of Observations") +
  scale_fill_gradient(low = "lightpink", high = "navy",
                      name = "Number of \nObservations") +
  xlab("Highway MPG") +
  ylab("Observations") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5),
        axis.ticks = element_blank())

# The graph is positively skewed, with a right-hand tail, the majority of the
# data falling on the left side. The majority of the observations are in the
# middle of the MPG range, with one big outlier at 16 MPG.

# Part 3
cyl.list = unique(mpg$cyl)
mpg_discrete <- mpg
mpg_discrete$cyl <- as.factor(mpg_discrete$cyl)
cyl_labels <- levels(mpg_discrete$cyl)

ggplot(mpg_discrete, aes(x = hwy))+
  geom_histogram(aes(fill = cyl), position = position_stack(), binwidth = 2) +
  ggtitle("Highway MPG vs. Number of Observations") +
  xlab("Highway MPG") +
  ylab("Observations") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5),
        axis.ticks = element_blank()) +
  scale_fill_manual(name = "Number of \nCylinders", labels = cyl_labels,
                    values = c("tomato","yellowgreen","steelblue", "skyblue"))

# The histogram clearly shows that reducing engine cylinders results in
# increased fuel efficiency, as less fuel is injected into the cylinders,
# resulting in lower amounts of fuel consumed.

# Part 4
ggplot(mpg, aes(x = cty, fill = ..count..))+
  geom_histogram(binwidth = 4)+
  ggtitle("City MPG vs. Number of Observations") +
  scale_fill_gradient(low = "lightpink", high = "navy",
                      name = "Number of \nObservations") +
  xlab("City MPG") +
  ylab("Observations") +
  theme_classic() +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

# The data is a positively skewed normal distribution, with a tail on the right.
# The majority of the data appears on the left side of the graph (between 10 and
# 20 MPG).

# Part 6
cyl_count <- as.data.frame(mpg %>% group_by(class, cyl) %>%
                             summarise(count = n()))
cyl_count$cyl <- as.factor(cyl_count$cyl)

ggplot(cyl_count, aes(x = class, y = count, fill = cyl)) +
  geom_bar(position = "stack", stat = "identity") +
  ggtitle("Car Class vs. Observations") +
  xlab("Car Class") +
  ylab("Observations") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5)) +
  scale_fill_manual(name = "Number of \nCylinders",
                    labels = cyl_labels,
                    values = c("tomato2", "yellowgreen", "cornflowerblue",
                               "steelblue", "slateblue4"))

# Part 7
ggplot(cyl_count, aes(x = reorder(class, count, sum), y = count,
                      fill = cyl)) +
  geom_bar(position = "stack", stat = "identity") +
  ggtitle("Car Class vs. Observations") +
  xlab("Car Class") +
  ylab("Observations") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5)) +
  scale_fill_manual(name = "Number of \nCylinders",
                    labels = cyl_labels,
                    values = c("tomato2", "yellowgreen", "cornflowerblue",
                               "steelblue", "slateblue4"))

# The SUV is the most popular class, while the two-seater is the least popular.
# 4-cylinder engines seem to be more prevalent in compact, subcompact, and
# midsize vehicles, although they are less common in minivans, pickup trucks,
# and SUVs. 4-cylinder engines are non-existent in two-seaters, which are always
# powered by 8-cylinder engines. 4-cylinder engines are used in all the popular
# types of vehicles, with the exception of the minivan.

# Part 8
# This appears to be an accented colour scale, which is used as a tool to
# highlight. Its use here is good as it helps to highlight the more fuel
# efficient and popular engines, and distinguish them from the higher
# performance engines with 6+ cylinders.

# Part 9
mpg.median <- mpg %>% select(cty, hwy, fl) %>% group_by(fl) %>% summarise(
  median_cty = median(cty),
  median_hwy = median(hwy)) 

mpg_median_tbl <- mpg.median %>% select(median_cty, median_hwy, fl) %>% gather(
  key = "two_variables", value = "their_values", -fl)

ggplot(mpg_median_tbl, aes(x = reorder(fl, their_values), y = their_values,
                 group = two_variables, fill = two_variables)) +
  geom_bar(position = "dodge", stat = "identity") +
  ggtitle("Fuel Type vs. Median MPG") +
  xlab("Fuel Type") +
  ylab("Median MPG") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5)) +
  scale_fill_manual(name = "Median MPG",
                    labels = c("City", "Highway"),
                    values = c("darkred","orange"))

# Part 10
# The highway MPG for each type of fuel is greater than the city MPG. The lowest
# performing fuel type is E, while the top performing fuel type is D. Highway
# MPG is usually higher since the car does not need to accelerate and decelerate
# as much as it does in city driving.

# Part 11
ggplot(mpg, aes(x = manufacturer, y = cty, fill = manufacturer)) +
  geom_boxplot() +
  ggtitle("Manufacturer vs City MPG") +
  xlab("Manufacturer") +
  ylab("City MPG") +
  theme(plot.title = element_text(size = 12, face = "bold", hjust=0.5),
        axis.text.x = element_text(angle = 45, size = 10, hjust = 0.5),
        axis.title.y = element_text(size = 10, hjust = 0.5))

# The plot shows that most manufacturers have cars with city MPGs ranging from
# 10 to 20, with Honda and Volkswagen, which has three cars over the 25 MPG
# range. Most manufactures have higher variation from the Q2, with only Land
# Rover, Lincoln, and Mercury showing tight uniformity of MPG within their
# vehicles. Toyota has long whiskers, indicating a wide range of city MPG
# ratings in their vehicles.