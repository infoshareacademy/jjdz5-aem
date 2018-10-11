<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Currency Manager</title>
    <style>
        .container .row #footer {
            padding: 20%;
            border: 20%;
        }

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
            width: 50%;
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
<#include "menu.html">

<div class="container">
    <div class="row">
        <div class="col-4" id="main">
            <h4>Currency Converter</h4>
        </div>

        <div class="col-7" id="main1">
            <h4>Exchange Rate</h4>
        </div>
    </div>

    <div class="row">
        <div class="col-4">
            <form method="post" action="/calculator">
                <div class="form-group">
                    <label for="amount">Amount</label>
                    <input id="amount"
                           required
                           type="number"
                           step="0.01"
                           min="0"
                           name="amount"
                    value="${score.getAmount()?long?c}"
                    class="form-control" />
                </div>
                <div class="form-group">

                    <div class="form-group">
                        <label for="currencyIHave"> Currency I Have</label>
                        <select class="form-control" id="currencyIHave" name="have">
                             <#list singleCurrency as s>
                                 <option
                                 <#if s.getName()== score.getCurrencyHave()>
                                   selected </#if>
                                 >${s.getName()} - ${s.getCurrencyNameCountryFlags().getCurrency()}
                                 </option>
                             </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">

                    <div class="form-group">
                        <label for="currencyIWant"> Currency I Want</label>
                        <select class="form-control" id="currencyIWant" name="want">
                              <#list singleCurrency as s>
                                  <option
                                <#if s.getName()== score.getCurrencyWant()>
                                 selected </#if>
                                  >${s.getName()} - ${s.getCurrencyNameCountryFlags().getCurrency()}</option>
                              </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date">Date</label>
                    <input id="date"
                           type="date"
                           name="date"
                           min=${score.getMinDate()} max=${score.getMaxDate()}
                           value=${score.getDateExchange()}
                                   class="form-control"/>
                </div>
                <button type="submit" class="btn btn-primary" value="result">Convert</button>

                 <#if score.getScore()??>
                <div>
                    <label for="result" class="result"><strong>Result</strong></label>
                    <div>On date: ${score.getDateExchange()}</div>
                    <div>
                        <marquee><strong>Your exchange:</strong> ${score.getAmount()} ${score.getCurrencyHave()}
                            = ${score.getScore()} ${score.getCurrencyWant()}
                            <strong>Unit exchange rate:</strong> 1 ${score.getCurrencyHave()}
                            =  ${score.getCourseValue()}  ${score.getCurrencyWant()} </marquee>
                    </div>
                </div>
                 </#if>
            </form>
        </div>
        <div class="col-7" id="main_currency">
            <form>
                <div class="form-group">

                    <label for="defult" id="label-defult">Currency</label>
                    <select class="form-control" id="defult" name="currency_table">
                     <#list singleCurrency as s>
                        <option
                        <#if s.getName()== currencyInTable>
                            selected </#if>
                              >${s.getName()} - ${s.getCurrencyNameCountryFlags().getCurrency()}</option>
                               </#list>
                    </select>
                </div>

            </form>

            <h5>Main Currencies</h5>

            <table class="tabela2" cellspacing='0'>
                <thead>
                <tr>
                    <th scope="col">Currency</th>
                    <th scope="col">Name</th>
                    <th scope="col">Country</th>
                    <th scope="col">Current rate</th>
                    <th scope="col">Date range</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>USA <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/usd.svg" width="30" alt=""></td>
                    <td>USA Dolar</td>
                    <td>USA</td>
                    <td>3.09</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                <tr>
                    <td>EUR <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/eur.svg" width="30" alt=""></td>
                    <td>Euro</td>
                    <td>Europe</td>
                    <td>4.20</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                <tr>
                    <td>GBP <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/gbp.svg" width="30" alt=""></td>
                    <td>British Pound</td>
                    <td>Great Britain</td>
                    <td>4.20</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-4">

        </div>

        <div class="col-7" id="table_remining">
            <h5>Other Currency</h5>
            <table class="tabela2" cellspacing='0'>
                <thead>
                <tr>
                    <th scope="col">Currency</th>
                    <th scope="col">Name</th>
                    <th scope="col">Country</th>
                    <th scope="col">Current rate</th>
                    <th scope="col">Date range</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>USA <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/usd.svg" width="30" alt=""></td>
                    <td>USA Dolar</td>
                    <td>USA</td>
                    <td>3.09</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                <tr>
                    <td>EUR <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/eur.svg" width="30" alt=""></td>
                    <td>Euro</td>
                    <td>Europe</td>
                    <td>4.20</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                <tr>
                    <td>GBP <img class="FlagImage-f4dhzl-0 dhyaOy Image-uvbiv3-0 hdHPqe"
                                 src="https://www.xe.com/themes/xe/images/flags/svg/gbp.svg" width="30" alt=""></td>
                    <td>British Pound</td>
                    <td>Great Britain</td>
                    <td>4.20</td>
                    <td>2010.01.01 do 2018.08.01</td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</div>
<div class="row" id="footer">
    <div class="col-11">
        <h5></h5>
    </div>
</div>
</div>
 <#include "footer.html">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="scripts.js"></script>

</body>
</html>