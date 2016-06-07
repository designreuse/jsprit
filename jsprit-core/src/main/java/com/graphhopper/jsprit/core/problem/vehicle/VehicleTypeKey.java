/*******************************************************************************
 * Copyright (c) 2014 Stefan Schroeder.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Stefan Schroeder - initial API and implementation
 ******************************************************************************/
package com.graphhopper.jsprit.core.problem.vehicle;

import com.graphhopper.jsprit.core.problem.AbstractVehicle;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.Skills;

/**
 * Key to identify similar vehicles
 * <p>
 * <p>Two vehicles are equal if they share the same type, the same start and end-location and the same earliestStart and latestStart.
 *
 * @author stefan
 */
public class VehicleTypeKey extends AbstractVehicle.AbstractTypeKey {

    public final String type;
    public final Location startLocation;
    public final Location endLocation;
    public final double earliestStart;
    public final double latestEnd;
    public final Skills skills;
    public final boolean returnToDepot;
    public Double maximumRouteDuration = null;

    public VehicleTypeKey(String typeId, Location startLocation, Location endLocation, double earliestStart, double latestEnd, Skills skills, boolean returnToDepot, Double maximumRouteDuration) {
        super();
        this.type = typeId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.earliestStart = earliestStart;
        this.latestEnd = latestEnd;
        this.skills = skills;
        this.returnToDepot = returnToDepot;
        this.maximumRouteDuration = maximumRouteDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleTypeKey that = (VehicleTypeKey) o;

        if (Double.compare(that.earliestStart, earliestStart) != 0) return false;
        if (Double.compare(that.latestEnd, latestEnd) != 0) return false;
        if (returnToDepot != that.returnToDepot) return false;
        if (endLocation != null && that.endLocation != null && !endLocation.getId().equals(that.endLocation.getId())) return false;
        if (!skills.equals(that.skills)) return false;
        if (startLocation != null && that.endLocation != null && !startLocation.getId().equals(that.startLocation.getId())) return false;
        if (!type.equals(that.type)) return false;
        if (that.maximumRouteDuration == null ^ maximumRouteDuration == null) return false;
        if (that.maximumRouteDuration != null && maximumRouteDuration != null
                && Double.compare(that.maximumRouteDuration, maximumRouteDuration) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        if(startLocation != null)
        result = 31 * result + startLocation.getId().hashCode();
        if(endLocation != null)
        result = 31 * result + endLocation.getId().hashCode();
        temp = Double.doubleToLongBits(earliestStart);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latestEnd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + skills.hashCode();
        result = 31 * result + (returnToDepot ? 1 : 0);
        if(maximumRouteDuration != null)
            temp = Double.doubleToLongBits(maximumRouteDuration);
        else
            temp = Double.doubleToLongBits(Double.MAX_VALUE);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(type);
        if(startLocation != null)
            stringBuilder.append("_").append(startLocation.getId());
        if(endLocation != null)
            stringBuilder.append("_").append(endLocation.getId());
        stringBuilder.append("_").append(Double.toString(earliestStart)).append("_").append(Double.toString(latestEnd));
        if(maximumRouteDuration != null)
            stringBuilder.append("_").append(maximumRouteDuration);
        return stringBuilder.toString();
    }


}
