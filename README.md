# Acquiring weather data using Scala
The purpose of this project is to obtain meteorological data from DMI in order to get an overview of the weather for some historical period. 

DMI has an available REST-API: https://confluence.govcloud.dk/pages/viewpage.action?pageId=26476690

# Quick start (Linux):
1. clone this repository `git clone https://github.com/wizDs/weather_data_dmi.git`
2. change directory to cloned repository `cd {path to directory}/weather_data_dmi`
3. set environment variables, defining the , eg. `source parameters` (for overview look at [ClimateParameterNames.scala](./src/main/scala/weather/ClimateParameterNames.scala) and [Parameters.scala](./src/main/scala/weather/Parameters.scala))
4. set api key for the dmi services as an environment variable `export API_KEY={your api key}`
5. set the path where the data  as an environment variable `export PATH_DATA={your desired path}`
6. run using `sbt run`


## How to define the time period
There are four ways to specify the time period according to the confluence documentation.
1. datetime = {startDate}/{endDate}
2. datetime = ../{endDate}
3. datetime = {startDate}/..
4. datetime = {date}

How to set the dmi datetime parameter according to cases:
1. `export START_DATE="{startdate}"` and `export END_DATE="{enddate}"` 
2. `export START_DATE=".."` and `export END_DATE="{enddate}"`
3. `export START_DATE="{startdate}"` and `export END_DATE=".."`
4. `export DATE="{single date}"`