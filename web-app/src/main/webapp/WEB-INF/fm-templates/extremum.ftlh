<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Local Extremum</title>
    <style>

        .container .row #main{
            background-color: dimgrey;
            color: white;
            padding: 5px 15px;
            border-radius: 2px;
            text-align: center;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
            margin: 1.0em;
            margin-left: 0em;
            margin-right: 0em;
            position: relative;
            right: 2px;
        }

        .container .row #main1{
            background-color: dimgrey;
            color: white;
            padding: 5px 15px;
            border-radius: 2px;
            text-align: center;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
            margin: 1.0em;
            margin-left: 0em;
            margin-right: 0em;
            position: relative;
            left: 2px;
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
            padding-left: 0.6em;
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
            font-size: 16px;
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
            <h4>Exchange Rate Extremums</h4>
        </div>
    </div>

    <form method="post" action="/extremum" class="needs-validation" id="form-extremum" name="formExtremum" onsubmit=setRadiosOnSubmit()>
        <div class="row">
            <div class="col-4">
                <div class="form-group">
                    <label for="currencyIWant">Currency Selection</label>
                    <select class="form-control" id="currencyIWant" name="currencyName">
                        <#list currencyRepository.getCurrenciesWithFullNameAndFlag() as ac>
                            <option
                                <#if ac.getName() == extremumObject.getCurrencyName()>
                                    selected </#if>>
                                ${ac.getName()} - ${ac.getCurrencyNameCountryFlags().getCurrency()}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="col-5">
                <div class="alert alert-info" role="alert" style="padding-top: 6px; padding-bottom: 6px">
                    <i class="fas fa-info-circle" style="margin-right:5px"></i>
                    1 PLN is the base unit for all the values shown below
                </div>
            </div>
            <div class="col-3">
                <div class="form-group" id=show-extremums">
                    <button type="submit" class="btn btn-primary" id="submit-button" value="extremums" onsubmit="setDatesOnSubmit()">Show Extremum</button>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" class="custom-control-input" name="extremumRadios" id="globalRadio" value="globalRadioChecked">
                    <label class="custom-control-label" for="globalRadio">Global Date Range</label>
                    <div style="font-size: 16px">
                        <i class="fas fa-question-circle fa:2x" style="margin-left:10px" data-toggle="tooltip" data-placement="right"
                           title="Exchange rate extremums from within range of dates covering the whole history. Dates cannot be modified in this mode"
                           trigger="click hover">
                        </i>
                    </div>
                </div>
                <div class="custom-control custom-radio custom-control-inline" >
                    <input type="radio" class="custom-control-input" name="extremumRadios" id="localRadio" value="localRadioChecked">
                    <label class="custom-control-label" for="localRadio">Local Date Range</label>
                    <div style="font-size: 16px">
                        <i class="fas fa-question-circle fa:2x" style="margin-left:19px" data-toggle="tooltip" data-placement="right"
                           title="Exchange rate extremums from within range of dates chosen by the user"
                           trigger="click hover">
                        </i>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <div class="form-group">
                    <label for="date">Date From</label>
                    <#if dateFromAfterDateTo == false>
                        <input type="date" class="form-control" id="date-from" name="dateFrom" required
                               min=${currencyRepository.getFirstDateFromRepository()}
                               max=${currencyRepository.getLastDateFromRepository()}
                               value=${extremumObject.getDateFrom()}>
                    <#else>
                        <input type="date" class="form-control is-invalid" id="date-from-invalid" name="dateFrom" required
                               min=${currencyRepository.getFirstDateFromRepository()}
                               max=${currencyRepository.getLastDateFromRepository()}
                               value=${extremumObject.getDateFrom()}>
                            <div class="invalid-feedback">
                                <b>Date From</b> is after <b>Date To</b>. Please correct and try again!
                            </div>
                    </#if>
                </div>
            </div>
            <div class="col-4">
                <div class="form-group">
                    <label for="date">Date To</label>
                    <#if dateFromAfterDateTo == false>
                        <input type="date" class="form-control" id="date-to" name="dateTo" required
                                min=${currencyRepository.getFirstDateFromRepository()}
                                max=${currencyRepository.getLastDateFromRepository()}
                                value=${extremumObject.getDateTo()}>
                    <#else>
                        <input type="date" class="form-control is-invalid" id="date-to-invalid" name="dateTo" required
                               min=${currencyRepository.getFirstDateFromRepository()}
                               max=${currencyRepository.getLastDateFromRepository()}
                               value=${extremumObject.getDateTo()}>
                    </#if>
                </div>
            </div>
            <div class="col-4">
                <p id="demo"></p>
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
                <#list currencyRepository.getCurrenciesWithFullNameAndFlag() as ac>
                <#list extremumObject.getMinExtremum() as minCur>
                <tr>
                    <#if ac.getName() == extremumObject.getCurrencyName()>
                        <td><img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src=" ${ac.getCurrencyNameCountryFlags().getUrl()}" width="20" alt=""> ${ac.getName()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCurrency()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCountry()}</td>
                        <td>${minCur.getClose()}</td>
                        <td>${minCur.getDate()}</td>
                    </#if>
                </tr>
                </#list>
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
                <#list currencyRepository.getCurrenciesWithFullNameAndFlag() as ac>
                <#list extremumObject.getMaxExtremum() as maxCur>
                <tr>
                    <#if ac.getName() == extremumObject.getCurrencyName()>
                        <td><img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src=" ${ac.getCurrencyNameCountryFlags().getUrl()}" width="20" alt=""> ${ac.getName()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCurrency()}</td>
                        <td>${ac.getCurrencyNameCountryFlags().getCountry()}</td>
                        <td>${maxCur.getClose()}</td>
                        <td>${maxCur.getDate()}</td>
                    </#if>
                </tr>
                </#list>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    var dateFrom = "${extremumObject.getDateFrom()}";
    var dateTo = "${extremumObject.getDateTo()}";
    var radioChecked = "${radioChecked}";

    document.getElementById("globalRadio").addEventListener("click", prepareFormOnGlobalClick);
    document.getElementById("localRadio").addEventListener("click", prepareFormOnLocalClick);
    window.onload = prepareFormOnLoad();

    function prepareFormOnLoad() {
        if (radioChecked === "globalRadioChecked") {
            document.getElementById("globalRadio").checked = true;
            setDatesReadOnlyTrue();
        } else {
            document.getElementById("localRadio").checked = true;
            setDatesReadOnlyFalse();
        }
    }

    function prepareFormOnGlobalClick() {
        setDatesReadOnlyTrue();
        document.getElementById("date-from").value = "${currencyRepository.getFirstDateFromRepository()}";
        document.getElementById("date-to").value = "${currencyRepository.getLastDateFromRepository()}";
    }

    function prepareFormOnLocalClick() {
        setDatesReadOnlyFalse();
        document.getElementById("date-from").value = dateFrom;
        document.getElementById("date-to").value = dateTo;
    }

    function setDatesReadOnlyTrue() {
        document.getElementById("date-from").readOnly = true;
        document.getElementById("date-to").readOnly = true;
    }

    function setDatesReadOnlyFalse() {
        document.getElementById("date-from").readOnly = false;
        document.getElementById("date-to").readOnly = false;
    }

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.5.0/js/all.js" integrity="sha384-GqVMZRt5Gn7tB9D9q7ONtcp4gtHIUEW/yG7h98J7IpE3kpi+srfFyyB/04OV6pG0" crossorigin="anonymous"></script>
<#include "footer.html">
</body>
</html>