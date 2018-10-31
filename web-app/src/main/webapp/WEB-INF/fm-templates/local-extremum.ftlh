<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Local Extremum</title>
    <style>

        .container .row #main, .container .row #main1 {

            background-color: dimgrey;

            color: white;
            padding: 5px 15px;

            border-radius: 2px;
            text-align: center;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
            margin: 1.0em;

        }

        .container .row #main h4, .container .row #main1 h4 {
            font-family: inherit;
            font-size: 1.2rem;
            font-weight: bold;
        }

        .form-control#defult {
            display: inline-block;
            width: 30%;
        }

        #label-defult {
            padding: 10px;
        }

        .container h5 {
            font-family: inherit;
            font-size: 1.2rem;
            font-weight: bold;
            color: gray;
            padding: 1.0em;
        }

        .container .col-7#main_currency, .container .col-7#table_remining {

            margin-left: 2em;
        }

        .result {
            margin-top: 2.0em;
        }

        .tabela2 {
            font-family: Arial, Helvetica, sans-serif;
            color: #666;
            font-size: 12px;
            text-shadow: 1px 1px 0px #fff;
            background: #eaebec;
            margin: 10px;
            border: #ccc 1px solid;
            border-collapse: separate;
            width: 100%;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;

            -moz-box-shadow: 0 1px 2px #d1d1d1;
            -webkit-box-shadow: 0 1px 2px #d1d1d1;
            box-shadow: 0 1px 2px #d1d1d1;
        }

        .tabela2 th {
            font-weight: bold;
            padding: 21px 25px 22px 25px;
            border-top: 1px solid #fafafa;
            border-bottom: 1px solid #e0e0e0;
            border-left: 1px solid #e0e0e0;

            background: #ededed;
            background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
            background: -moz-linear-gradient(top, #ededed, #ebebeb);
        }

        .tabela2 th:first-child {
            text-align: left;
            padding-left: 20px;
        }

        .tabela2 tr:first-child th:last-child {
            -moz-border-radius-topright: 3px;
            -webkit-border-top-right-radius: 3px;
            border-top-right-radius: 3px;
        }

        .tabela2 tr {
            text-align: center;
            padding-left: 20px;
        }

        .tabela2 tr td:first-child {
            text-align: left;
            padding-left: 20px;
            border-left: 0;
        }

        .tabela2 tr td {
            padding: 18px;
            background-color: white;
            border-top: 1px solid #ffffff;
            border-bottom: 1px solid #e0e0e0;
            border-left: 1px solid #e0e0e0;

            background: white;
            background: -webkit-gradient(linear, left top, left bottom, from(white), to(white));
            background: -moz-linear-gradient(top, white, white);
        }

        .tabela2 tr:last-child td {
            border-bottom: 0;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>

</head>

<body>
<h6 align="right">User: ${logged}</h6>
<#include "manager-menu.html">

<div class="container">
    <div class="row">
        <div class="col-12" id="main">
            <h4>Local Extremum</h4>
        </div>
    </div>
    <form method="post" action="/local-extremum">
        <div class="row">
            <div class="col-4">
                <div class="form-group">
                    <label for="currencyIWant">Currency Selection</label>
                    <select class="form-control" id="currencyIWant" name="currencyName">
                        <#list currenciesWithFullNameAndFlag as ac>
                            <option
                                <#if ac.getName() == currencyName>
                                    selected </#if>>
                                ${ac.getName()} - ${ac.getCurrencyNameCountryFlags().getCurrency()}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="col-4">
                <div class="form-group">
                </div>
            </div>
            <div class="col-1">
                <div class="form-group">
                </div>
            </div>
            <div class="col-3">
                <div class="form-group" id=show-extremums">
                    <button type="submit" class="btn btn-primary" value="extremums">Show Extremums</button>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <div class="form-group">
                    <label for="date">Date From</label>
                    <input id="date"
                           type="date"
                           name="dateFrom"
                           min=${currencyRepository.getFirstDateFromRepository()} max=${currencyRepository.getLastDateFromRepository()}
                           value=${dateFrom}
                                   class="form-control"/>
                </div>
            </div>
            <div class="col-4">
                <div class="form-group">
                    <label for="date">Date To</label>
                    <input id="date"
                           type="date"
                           name="dateTo"
                           min=${currencyRepository.getFirstDateFromRepository()} max=${currencyRepository.getLastDateFromRepository()}
                           value=${dateTo}
                                   class="form-control"/>
                </div>
            </div>
            <div class="col-1">
                <div class="form-group">
                </div>
            </div>
            <div class="col-3">
                <div class="form-group">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-8">
                <#if isDateFromAfterDateTo>
                    <div class="alert alert-danger">
                        Date From is after Date To. Try again!
                    </div>
                </#if>
            </div>
            <div class="col-4">
            </div>
        </div>
    </form>

    <div class="row">
        <div class="col-12" id="main_currency">

            <h5>Mimimum</h5>

            <table class="tabela2" cellspacing='0'>
                <thead>
                <tr>
                    <th scope="col">Currency</th>
                    <th scope="col">Name</th>
                    <th scope="col">Country</th>
                    <th scope="col">Minimum Value</th>
                    <th scope="col">Dates of Occurence</th>
                </tr>
                </thead>
                <tbody>
                <#list currenciesWithFullNameAndFlag as ac>
                <tr>
                    <#if ac.getName() == currencyName>
                        <td><img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src=" ${ac.getCurrencyNameCountryFlags().getUrl()}" width="20" alt=""> ${ac.getName()}
                        </td>
                        <td>${ac.getCurrencyNameCountryFlags().getCurrency()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCountry()}</td>
                        <#list minExtremum as minCur>
                            <td>${minCur.getClose()}</td>
                            <td>${minCur.getDate()}</td>
                        </#list>
                    </#if>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-12" id="main_currency">

            <h5>Maximum</h5>

            <table class="tabela2" cellspacing='0'>
                <thead>
                <tr>
                    <th scope="col">Currency</th>
                    <th scope="col">Name</th>
                    <th scope="col">Country</th>
                    <th scope="col">Maximum Value</th>
                    <th scope="col">Dates of Occurence</th>
                </tr>
                </thead>
                <tbody>
                <#list currenciesWithFullNameAndFlag as ac>
                <tr>
                    <#if ac.getName() == currencyName>
                        <td><img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src=" ${ac.getCurrencyNameCountryFlags().getUrl()}" width="20" alt=""> ${ac.getName()}
                        </td>
                        <td>${ac.getCurrencyNameCountryFlags().getCurrency()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCountry()}</td>
                        <#list maxExtremum as maxCur>
                            <td>${maxCur.getClose()}</td>
                            <td>${maxCur.getDate()}</td>
                        </#list>
                    </#if>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script src="scripts.js"></script>
<#include "footer.html">
</body>
</html>