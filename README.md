# Acquiring weather data using Scala
The purpose of this project is to obtain meteorological data from DMI in order to get an overview of the weather for some historical period.

DMI has an available REST-API: https://confluence.govcloud.dk/pages/viewpage.action?pageId=26476690

# Quick start (Linux):
1. git clone repository
2. change directory to cloned repository
3. set environments, eg. `source parameters.sh` (for overview look at [ClimateParameterNames.scala](./src/main/scala/weather/ClimateParameterNames.scala) and [Parameters.scala](./src/main/scala/weather/Parameters.scala))
4. set API_KEY environment variable
5. run using `sbt run`

## Defining the period
There are four ways to determine the period according to the specs in confluence.
1. datetime = {startDate}/{endDate}
2. datetime = ../{endDate}
3. datetime = {startDate}/..
4. datetime = {date}

How to set the dmi datetime parameter:
1. set START_DATE and END_DATE environment variable
2. set START_DATE='..' and END_DATE environment variable
3. set START_DATE and END_DATE='..' environment variable
4. set DATE environment variable

## Parameters
- PARAMETER_ID
- TIME_RESOLUTION
- STATION_ID
- LIMIT
- START_DATE
- END_DATE
- DATE

