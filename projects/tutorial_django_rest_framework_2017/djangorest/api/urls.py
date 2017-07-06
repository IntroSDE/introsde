from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from .views import PersonViewAll, HProfileViewAll, DetailsPersonView, HProfileViewEdit, HProfileViewHistory, HProfileViewLast

"""Defined URLs for the classes in views.py"""
urlpatterns = {
        url(r'^lifecoach/person/$', PersonViewAll.as_view(), name="create"),
        url(r'^lifecoach/person/(?P<pk>[0-9]+)/$', DetailsPersonView.as_view(), name="details"),
        url(r'^lifecoach/person/(?P<pk>[0-9]+)/health-profile/$', HProfileViewLast.as_view(), name="details"),
        url(r'^lifecoach/person/(?P<pk>[0-9]+)/health-profile/history', HProfileViewHistory.as_view(), name="details"),
        url(r'^lifecoach/health-profiles/$', HProfileViewAll.as_view(), name="create"),
        url(r'^lifecoach/health-profiles/edit/$', HProfileViewEdit.as_view(), name="create"),

}

urlpatterns = format_suffix_patterns(urlpatterns)
